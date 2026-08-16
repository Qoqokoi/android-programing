package com.qoqokoi.librepulse.ui

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.qoqokoi.librepulse.data.local.DeviceEntity
import com.qoqokoi.librepulse.databinding.ItemDeviceBinding

class DashboardAdapter : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    private var list = listOf<DeviceEntity>()

    fun submitList(newList: List<DeviceEntity>) {
        list = newList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemDeviceBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDeviceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val device = list[position]
        holder.binding.apply {
            tvHostname.text = device.hostname
            tvIpAddress.text = "Host: ${device.ip}"

            val isUp = device.status.uppercase() == "UP"
            tvStatusBadge.text = if (isUp) "ONLINE" else "OFFLINE"

            val bgShape = GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
                cornerRadius = 16f
                setColor(if (isUp) Color.parseColor("#2E7D32") else Color.parseColor("#C62828"))
            }
            tvStatusBadge.background = bgShape

            // Dead-Host Safety Handler check
            if (isUp && device.portsUp != null && device.portsDown != null) {
                tvPortUp.text = "Up: ${device.portsUp}"
                tvPortDown.text = "Down: ${device.portsDown}"
            } else {
                tvPortUp.text = "Up: -"
                tvPortDown.text = "Down: -"
            }
        }
    }

    override fun getItemCount() = list.size
}
