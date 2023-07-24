package com.example.infinite_scroll

import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.infinite_scroll.databinding.ItemListBinding

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    var datalist = mutableListOf<DogData>()

    inner class MyViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(dogData:DogData){
            binding.dogBreedTv.text=dogData.dog_name
            binding.dogAgeTv.text= dogData.dog_age.toString()
            binding.dogGenderTv.text=dogData.dog_gender
        }
    }


    //만들어진 뷰홀더 없을때 뷰홀더(레이아웃) 생성하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding=ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int =datalist.size

    //recyclerview가 viewholder를 가져와 데이터 연결할때 호출
    //적절한 데이터를 가져와서 그 데이터를 사용하여 뷰홀더의 레이아웃 채움
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(datalist[position])
    }
}