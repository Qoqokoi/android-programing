package com.qoqokoi.librepulse.ui

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.qoqokoi.librepulse.data.local.PortEntity
import com.qoqokoi.librepulse.databinding.ItemPortDetailBinding

class PortDetailAdapter : RecyclerView.Adapter<PortDetailAdapter.ViewHolder>() {

    private var list = listOf<PortEntity>()

    fun submitList(newList: List<PortEntity>) {
        list = newList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemPortDetailBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPortDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val port = list[position]
        holder.binding.apply {
            tvPortTitle.text = port.ifName

            val ip = port.ipAddress
            when {
                ip != null && ip.startsWith("Bridged") -> {
                    tvPortIp.text = ip
                    tvPortIp.setTextColor(Color.parseColor("#00ACC1")) // Cyan untuk Bridged
                }
                !ip.isNullOrBlank() -> {
                    tvPortIp.text = "IP: $ip"
                    tvPortIp.setTextColor(Color.parseColor("#00E676")) // Hijau untuk Routed IP
                }
                else -> {
                    tvPortIp.text = "IP: Unassigned (Layer 2)"
                    tvPortIp.setTextColor(Color.parseColor("#777777"))
                }
            }

            val speed = port.ifSpeed ?: 0L
            val speedText = when {
                speed >= 1_000_000_000L -> "${speed / 1_000_000_000L} Gbps"
                speed >= 1_000_000L -> "${speed / 1_000_000L} Mbps"
                speed > 0 -> "$speed bps"
                else -> "Auto"
            }
            val alias = port.ifAlias?.takeIf { it.isNotBlank() } ?: port.ifDescr ?: "Physical Port"
            tvPortMeta.text = "$speedText • $alias"

            val oper = port.ifOperStatus?.lowercase()?.trim() ?: ""
            val isUp = oper == "up" || oper == "1"

            tvPortBadge.text = if (isUp) "UP" else "DOWN"

            val bg = GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
                cornerRadius = 12f
                setColor(if (isUp) Color.parseColor("#2E7D32") else Color.parseColor("#C62828"))
            }
            tvPortBadge.background = bg
        }
    }

    override fun getItemCount() = list.size
}
