package ru.acediat.feature_profile.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import ru.acediat.core_android.AdapterCallback
import ru.acediat.core_android.Logger
import ru.acediat.core_android.OSS_TAG
import ru.acediat.core_android.RecyclerViewAdapter
import ru.acediat.feature_profile.model.dtos.ProductDTO
import ru.acediat.feature_profile.R
import ru.acediat.feature_profile.databinding.ItemProductBinding
import javax.inject.Inject

class ProductsAdapter @Inject constructor(
    private val picasso: Picasso
) : RecyclerViewAdapter<ProductDTO, ItemProductBinding>() {

    private var onProductClick: (ProductDTO) -> Unit = {}

    fun setOnProductClick(callback: (ProductDTO) -> Unit){
        onProductClick = callback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        position: Int
    ): ViewHolder<ProductDTO, ItemProductBinding> = ViewHolder(
        ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        object : AdapterCallback<ProductDTO, ItemProductBinding> {
            override fun bindViews(binding: ItemProductBinding, item: ProductDTO, position: Int) =
                with(binding){
                    productName.text = item.name
                    productCost.text = item.price.toString()
                    picasso.load(item.imageUrl)
                        .fit()
                        .centerInside()
                        .error(R.drawable.ic_store)//TODO: затычка, поставить что-то норм
                        .into(productImage)
                }

            override fun onViewClicked(view: View, item: ProductDTO) {
                onProductClick(item)
            }
        }
    )
}