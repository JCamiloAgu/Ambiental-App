package com.adsi.ambiental.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adsi.ambiental.R
import com.adsi.ambiental.models.User
import kotlinx.android.synthetic.main.item_ranking.view.*

class RecyclerAdapterRanking(private val ctx: Context): RecyclerView.Adapter<RecyclerAdapterRanking.ViewHolder>() {

    private val userTop = arrayListOf(User(id = 1, userName = "KMIILO92", createdAt = "d", isActive = true, score = "200 pts", updateAt = "dd"), User(id = 2, userName = "JOSExxsadaasx", createdAt = "d", isActive = true, score = "802 pts", updateAt = "dd"))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.item_ranking, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = userTop.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = userTop[position]
        holder.txtUser.text = current.userName
        holder.txtScore.text = current.score
    }

    inner class ViewHolder(item: View): RecyclerView.ViewHolder(item) {
        var txtUser: TextView = item.txtUserName

        var txtScore: TextView = item.txtScore
    }
}