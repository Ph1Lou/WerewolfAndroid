package fr.isima.ayangelophjambaud


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isima.ayangelophjambaud.models.Game
import fr.isima.ayangelophjambaud.models.PrettyEvent
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit


class DetailsViewItemAdapter internal constructor(mItemList: List<PrettyEvent>) :
    RecyclerView.Adapter<DetailsViewItemAdapter.DetailsHolder>()
{
    private val ItemList: List<PrettyEvent>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.game_item, parent, false)
        return DetailsHolder(view)
    }

    override fun onBindViewHolder(holder: DetailsHolder, position: Int) {
        val item: PrettyEvent = ItemList[position]

        holder.eventName.text = item.text

    }

    override fun getItemCount(): Int {
        return ItemList.size
    }

    inner class DetailsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var eventName: TextView


        init {
            eventName = itemView.findViewById(R.id.eventName)
        }
    }

    init {
        ItemList = mItemList
    }
}