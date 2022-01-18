package fr.isima.ayangelophjambaud.adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ImageSpan
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isima.ayangelophjambaud.R
import fr.isima.ayangelophjambaud.models.PlayerInfo
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

class PlayersViewItemAdapter internal constructor(mItemList: List<PlayerInfo>) :
    RecyclerView.Adapter<PlayersViewItemAdapter.PlayersHolder>()
{
    private lateinit var context: Context
    private val playersList: List<PlayerInfo> = mItemList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.players_item, parent, false)
        this.context = parent.context;
        return PlayersHolder(view)
    }

    override fun onBindViewHolder(holder: PlayersHolder, position: Int) {
        val item: PlayerInfo = playersList[position]
        holder.playerName.text = item.name + " : "
        holder.playerRole.text = item.roleTranslation + " "

        if (item.winner == 1) {
            var winIcon = ImageView(context)
            winIcon.setImageResource(R.drawable.ic_baseline_done_outline_24)
            holder.playerInfo.addView(winIcon)
        }
        else {
            var deathIcon = ImageView(context)
            deathIcon.setImageResource(R.drawable.ic_baseline_church_24)
            holder.playerInfo.addView(deathIcon)

            val duration: Long = item.deathTime.toLong()
            val hh: Long = TimeUnit.SECONDS.toHours(duration)
            val mm: Long = TimeUnit.SECONDS.toMinutes(duration) % 60
            val ss: Long = TimeUnit.SECONDS.toSeconds(duration) % 60
            var deathTime = TextView(context)
            deathTime.text = String.format(" (%02d:%02d:%02d)", hh, mm, ss)
            holder.playerInfo.addView(deathTime)

        }

    }

    override fun getItemCount(): Int {
        return playersList.size
    }

    inner class PlayersHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var playerName: TextView = itemView.findViewById(R.id.playerName)
        var playerRole: TextView = itemView.findViewById(R.id.playerRole)
        var playerInfo: LinearLayout = itemView.findViewById(R.id.playerInfo)

    }
}