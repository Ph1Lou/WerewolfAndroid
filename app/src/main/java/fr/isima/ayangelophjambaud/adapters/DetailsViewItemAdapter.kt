package fr.isima.ayangelophjambaud.adapters


import android.content.Context
import android.graphics.BitmapFactory
import android.text.Spannable
import android.text.SpannableString
import android.text.style.DynamicDrawableSpan
import android.text.style.ImageSpan
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isima.ayangelophjambaud.R
import fr.isima.ayangelophjambaud.models.PrettyEvent
import java.util.concurrent.TimeUnit


class DetailsViewItemAdapter internal constructor(mItemList: List<PrettyEvent>) :
    RecyclerView.Adapter<DetailsViewItemAdapter.DetailsHolder>()
{
    private lateinit var context: Context
    private val eventsList: List<PrettyEvent> = mItemList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.detail_item, parent, false)
        this.context = parent.context;
        return DetailsHolder(view)
    }

    override fun onBindViewHolder(holder: DetailsHolder, position: Int) {
        val item: PrettyEvent = eventsList[position]
        val duration: Long = item.timer.toLong()
        val hh: Long = TimeUnit.SECONDS.toHours(duration)
        val mm: Long = TimeUnit.SECONDS.toMinutes(duration) % 60
        val ss: Long = TimeUnit.SECONDS.toSeconds(duration) % 60
        var text = item.text
        for(i in item.players.indices){
            val player = item.players.get(i)
            val decodedString: ByteArray = Base64.decode(player.head, Base64.DEFAULT)
            val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
            //val d: Drawable = BitmapDrawable(context.resources, decodedByte)
            val spanString = SpannableString("${player.name}")
            spanString.setSpan(ImageSpan(context, decodedByte), player.name.length, player.name.length+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            text = text.replace("{$i}",spanString.toString())
        }

        holder.eventName.text = "${String.format("%02d:%02d:%02d", hh, mm, ss)} $text"
        holder.eventName.highlightColor = item.color

    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    inner class DetailsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var eventName: TextView = itemView.findViewById(R.id.eventName)


    }

}