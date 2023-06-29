package com.practicaltask.ui.dashborad.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practicaltask.databinding.ItemListProductsLayoutBinding

class UsersListAdapter(
    private val categoryList: ArrayList<User>
) : RecyclerView.Adapter<UsersListAdapter.VHolder>() {

    inner class VHolder(private val mBinding: ItemListProductsLayoutBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(position: Int, user: User) {
            mBinding.tvUserName.text = user.first_name
            Glide.with(mBinding.imgUser.context).load(user.avatar).into(mBinding.imgUser)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder(
            ItemListProductsLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.bind(position, categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }


}