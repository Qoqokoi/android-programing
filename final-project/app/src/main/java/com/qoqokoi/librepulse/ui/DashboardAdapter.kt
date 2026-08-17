package com.qoqokoi.librepulse.ui

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.qoqokoi.librepulse.data.local.DeviceEntity
import com.qoqokoi.librepulse.databinding.ItemDeviceBinding

class DashboardAdapter(
    private val onItemClick: (DeviceEntity) -> Unit
) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

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
            tvSysName.text = device.sysName
            tvIpAddress.text = "Host IP: ${device.ip}"

            val isUp = device.status.uppercase() == "UP"
            tvHardwareStatusBadge.text = if (isUp) "ONLINE" else "OFFLINE"

            val bgShape = GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
                cornerRadius = 16f
                setColor(if (isUp) Color.parseColor("#2E7D32") else Color.parseColor("#C62828"))
            }
            tvHardwareStatusBadge.background = bgShape

            if (isUp && device.portsUp != null && device.portsDown != null) {
                tvPortUpLabel.text = "Up: ${device.portsUp}"
                tvPortDownLabel.text = "Down: ${device.portsDown}"
            } else {
                tvPortUpLabel.text = "Up: -"
                tvPortDownLabel.text = "Down: -"
            }

            root.setOnClickListener {
                onItemClick(device)
            }
        }
    }

    override fun getItemCount() = list.size
}
