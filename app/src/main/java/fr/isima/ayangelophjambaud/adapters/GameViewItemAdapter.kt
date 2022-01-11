package fr.isima.ayangelophjambaud


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import fr.isima.ayangelophjambaud.fragments.DetailsFragment
import fr.isima.ayangelophjambaud.models.Game
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit


class GameViewItemAdapter internal constructor(mItemList: List<Game>) :
    RecyclerView.Adapter<GameViewItemAdapter.GameHolder>()
{
    private val ItemList: List<Game>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.game_item, parent, false)
        return GameHolder(view)
    }

    override fun onBindViewHolder(holder: GameHolder, position: Int) {
        val item: Game = ItemList[position]
        val pattern = "HH:mm dd-MM-yyyy"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val duration: Long = item.duration.toLong()
        val HH: Long = TimeUnit.SECONDS.toHours(duration)
        val MM: Long = TimeUnit.SECONDS.toMinutes(duration) % 60
        val SS: Long = TimeUnit.SECONDS.toSeconds(duration) % 60
        holder.textName.text = item.name
        holder.textDate.text = simpleDateFormat.format(item.date)
        holder.textDuration.text = String.format("%02d:%02d:%02d", HH, MM, SS)
        holder.textWinnerCamp.text = item.winnerCamp
        holder.textPlayerCount.text = item.playerSize.toString()
        val bundle= Bundle()
        bundle.putString("game_uuid",item.gameUUID.toString())
        holder.buttonDetails.setOnClickListener{
            it.findNavController().navigate(R.id.detailsLayout, bundle)
        }


    }

    override fun getItemCount(): Int {
        return ItemList.size
    }

    inner class GameHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textName: TextView
        var textDuration: TextView
        var textDate: TextView
        var textPlayerCount: TextView
        var textWinnerCamp: TextView
        var buttonPlayer: Button
        var buttonDetails: Button

        init {
            textName = itemView.findViewById(R.id.textName)
            textDuration = itemView.findViewById(R.id.textDuration)
            textDate = itemView.findViewById(R.id.textDate)
            textPlayerCount = itemView.findViewById(R.id.textPlayerCount)
            textWinnerCamp = itemView.findViewById(R.id.textWinnerCamp)
            buttonDetails = itemView.findViewById(R.id.buttonDetails)
            buttonPlayer = itemView.findViewById(R.id.buttonPlayers)
        }
    }

    init {
        ItemList = mItemList
    }
}