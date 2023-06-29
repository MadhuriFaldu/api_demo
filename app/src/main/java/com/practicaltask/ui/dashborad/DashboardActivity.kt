package com.practicaltask.ui.dashborad

import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.practicaltask.R
import com.practicaltask.api.local.Status
import com.practicaltask.base.BaseActivity
import com.practicaltask.databinding.ActivityDashboardBinding
import com.practicaltask.ui.dashborad.category.Category
import com.practicaltask.ui.dashborad.category.CategoryListAdapter
import com.practicaltask.ui.dashborad.users.User
import com.practicaltask.ui.dashborad.users.UsersGridAdapter
import com.practicaltask.ui.dashborad.users.UsersListAdapter
import com.practicaltask.utils.Util.dismissProgress
import com.practicaltask.utils.Util.showProgress
import com.practicaltask.utils.extensions.setSafeOnClickListener
import com.practicaltask.utils.extensions.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity :
    BaseActivity<ActivityDashboardBinding>(ActivityDashboardBinding::inflate) {

    private val mViewModel by viewModels<DashBoardViewModel>()

    private val userList = arrayListOf<User>()
    private var isLine = true

    override fun init() {

        setObserver()

        mViewModel.callCategoryList()
        mViewModel.callUsersList()

    }

    override fun setAllClickListener() {

        mBinding.icType.setSafeOnClickListener {
            isLine = !isLine
            if (isLine) {
                mBinding.icType.setImageResource(R.drawable.ic_grid)
            } else {
                mBinding.icType.setImageResource(R.drawable.ic_list)
            }
            showUsersList()
        }

    }

    private fun setObserver() {

        mViewModel.categoryResponse.observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    showProgress()
                }

                Status.SUCCESS -> {
                    dismissProgress()
                    it.data?.data?.let {
                        showCategoryList(it)
                    }
                }

                Status.ERROR -> {
                    dismissProgress()
                    it.message?.let {
                        showToast(it)
                    }
                }
            }
        }

        mViewModel.usersResponse.observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    //  showProgress()
                }

                Status.SUCCESS -> {
                    dismissProgress()
                    it.data?.data?.let {
                        userList.addAll(it)
                        showUsersList()
                    }
                }

                Status.ERROR -> {
                    dismissProgress()
                    it.message?.let {
                        showToast(it)
                    }
                }
            }
        }

    }

    private fun showCategoryList(data: ArrayList<Category>) {

        mBinding.rvCategory.apply {
            adapter = CategoryListAdapter(data)
        }

    }

    private fun showUsersList() {

        userList.let { data ->

            mBinding.tvTotalItem.text = "" + data.size + " Products Found"

            mBinding.rvUsers.apply {
                if (isLine){
                    layoutManager = LinearLayoutManager(this@DashboardActivity)
                    adapter = UsersListAdapter(data)
                }else{
                    layoutManager = GridLayoutManager(this@DashboardActivity,2)
                    adapter = UsersGridAdapter(data)
                }
            }
        }
    }

}