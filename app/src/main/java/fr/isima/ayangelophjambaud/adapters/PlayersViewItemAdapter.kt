package fr.isima.ayangelophjambaud.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isima.ayangelophjambaud.R
import fr.isima.ayangelophjambaud.models.PlayerInfo
import fr.isima.ayangelophjambaud.utils.PlayerUtils


class PlayersViewItemAdapter internal constructor(mItemList: List<PlayerInfo>) :
    RecyclerView.Adapter<PlayersViewItemAdapter.PlayersHolder>()
{
    private lateinit var context: Context
    private val playersList: List<PlayerInfo> = mItemList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.players_item, parent, false)
        this.context = parent.context
        return PlayersHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PlayersHolder, position: Int) {
        val item: PlayerInfo = playersList[position]
        holder.playerHead.setImageBitmap(PlayerUtils.getHead(context, item.head))
        holder.playerName.text = item.name + " : "
        holder.playerRole.text = item.roleTranslation + " "

        if (item.winner) holder.winIcon.visibility = View.VISIBLE




    }

    override fun getItemCount(): Int {
        return playersList.size
    }

    inner class PlayersHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var playerHead: ImageView = itemView.findViewById(R.id.playerHead)
        var playerName: TextView = itemView.findViewById(R.id.playerName)
        var playerRole: TextView = itemView.findViewById(R.id.playerRole)
        var winIcon: ImageView = itemView.findViewById(R.id.winIcon)

    }
}