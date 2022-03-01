package fr.isima.ayangelophjambaud.adapters


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import fr.isima.ayangelophjambaud.R
import fr.isima.ayangelophjambaud.models.Game
import fr.isima.ayangelophjambaud.utils.TimeUtils


class GameViewItemAdapter internal constructor(mItemList: List<Game>) :
    RecyclerView.Adapter<GameViewItemAdapter.GameHolder>()
{
    private lateinit var context: Context
    private val gamesList: List<Game> = mItemList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
        val view: View =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.game_item, parent, false)
        context = parent.context
        return GameHolder(view)
    }

    override fun onBindViewHolder(holder: GameHolder, position: Int) {
        val item: Game = gamesList[position]
        holder.textName.text = item.name
        holder.textDate.text = TimeUtils.getDate(item.date)
        holder.textDuration.text = TimeUtils.getDuration(item.duration.toLong())
        holder.textWinnerCamp.text = item.winnerCamp
        holder.textPlayerCount.text = context.getString(R.string.playersCount,item.playersCount)
        val bundle= Bundle()
        bundle.putString("game_uuid",item.gameUUID.toString()) //pass gameUuid through fragment
        holder.buttonDetails.setOnClickListener{
            it.findNavController().navigate(R.id.detailsFragment, bundle)
        }
        holder.buttonPlayers.setOnClickListener{
            it.findNavController().navigate(R.id.playersFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return gamesList.size
    }

    inner class GameHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textName: TextView = itemView.findViewById(R.id.textName)
        var textDuration: TextView = itemView.findViewById(R.id.textDuration)
        var textDate: TextView = itemView.findViewById(R.id.textDate)
        var textPlayerCount: TextView = itemView.findViewById(R.id.textPlayerCount)
        var textWinnerCamp: TextView = itemView.findViewById(R.id.textWinnerCamp)
        var buttonPlayers: Button = itemView.findViewById(R.id.buttonPlayers)
        var buttonDetails: Button = itemView.findViewById(R.id.buttonDetails)
    }

}