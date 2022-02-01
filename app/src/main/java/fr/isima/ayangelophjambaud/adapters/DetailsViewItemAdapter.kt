package fr.isima.ayangelophjambaud.adapters


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ImageSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import fr.isima.ayangelophjambaud.R
import fr.isima.ayangelophjambaud.models.PrettyEvent
import fr.isima.ayangelophjambaud.utils.ColorUtil
import fr.isima.ayangelophjambaud.utils.PlayerUtils
import java.util.concurrent.TimeUnit


class DetailsViewItemAdapter internal constructor(mItemList: List<PrettyEvent>) :
    RecyclerView.Adapter<DetailsViewItemAdapter.DetailsHolder>()
{
    private lateinit var context: Context
    private val eventsList: List<PrettyEvent> = mItemList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.detail_item, parent, false)
        this.context = parent.context
        return DetailsHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DetailsHolder, position: Int) {
        val item: PrettyEvent = eventsList[position]
        val duration: Long = item.timer.toLong()
        val hh: Long = TimeUnit.SECONDS.toHours(duration)
        val mm: Long = TimeUnit.SECONDS.toMinutes(duration) % 60
        val ss: Long = TimeUnit.SECONDS.toSeconds(duration) % 60
        var text = item.text
        val placeHolders = ArrayList<Pair<Int,Int>>()
        val images = ArrayList<Bitmap>()
        val stringBuilder = StringBuilder(text)

        //add playerName before head placeholder
        var i=0
        while(i < stringBuilder.length) {
            if (stringBuilder[i] == '{') {
                val name = " "+item.players[Integer.valueOf(stringBuilder[i+1].toString())].name
                stringBuilder.insert(i-1,name)
                i+=name.length
            }
            i++
        }

        //Add Timer before text
        text = String.format("%02d:%02d:%02d", hh, mm, ss) + " " + stringBuilder.toString()

        for(player in item.players){
            images.add(PlayerUtils.getHead(context, player.head))
        }

        //Store placeholder position and player index
        for(j in text.indices) {
            if (text[j] == '}') {
                placeHolders.add(Pair(j - 2, Integer.valueOf(text[j - 1].toString())))
            }
        }

        //val d: Drawable = BitmapDrawable(context.resources, decodedByte)
        val spanString = SpannableString(text)

        //Relace placeholder with bitmap image
        for(placeHolder in placeHolders){
            val image = ImageSpan(context,images[placeHolder.second])
            spanString.setSpan(image, placeHolder.first, placeHolder.first+3, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        }

        holder.eventName.text = spanString
        holder.eventName.setBackgroundColor(ContextCompat.getColor(context, ColorUtil.getBackGroundColor(item.color)))
        holder.eventName.setTextColor(ContextCompat.getColor(context, ColorUtil.getTextColor(item.color)))
    }


    override fun getItemCount(): Int {
        return eventsList.size
    }

    inner class DetailsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var eventName: TextView = itemView.findViewById(R.id.eventName)


    }

}