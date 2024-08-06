package com.example.chatwiseassignment.model

import android.os.Parcel
import android.os.Parcelable

data class ApiResponse(
    val products: List<Product>,
    val total: Long,
    val skip: Long,
    val limit: Long
)

  data class Product(
    val id: Long,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Long,
    val tags: List<String>,
    val brand: String?,
    val sku: String,
    val weight: Long,
    val dimensions: Dimensions,
    val warrantyInformation: String,
    val shippingInformation: String,
    val availabilityStatus: String,
    val reviews: List<Review>,
    val returnPolicy: String,
    val minimumOrderQuantity: Long,
    val meta: Meta,
    val images: List<String>,
    val thumbnail: String,
): Parcelable {
     constructor(parcel: Parcel) : this(
         parcel.readLong(),
         parcel.readString()!!,
         parcel.readString()!!,
         parcel.readString()!!,
         parcel.readDouble(),
         parcel.readDouble(),
         parcel.readDouble(),
         parcel.readLong(),
         parcel.createStringArrayList()!!,
         parcel.readString(),
         parcel.readString()!!,
         parcel.readLong(),
         parcel.readParcelable(Dimensions::class.java.classLoader)!!,
         parcel.readString()!!,
         parcel.readString()!!,
         parcel.readString()!!,
         parcel.createTypedArrayList(Review)!!,
         parcel.readString()!!,
         parcel.readLong(),
         parcel.readParcelable(Meta::class.java.classLoader)!!,
         parcel.createStringArrayList()!!,
         parcel.readString()!!
     ) {
     }

     override fun writeToParcel(parcel: Parcel, flags: Int) {
         parcel.writeLong(id)
         parcel.writeString(title)
         parcel.writeString(description)
         parcel.writeString(category)
         parcel.writeDouble(price)
         parcel.writeDouble(discountPercentage)
         parcel.writeDouble(rating)
         parcel.writeLong(stock)
         parcel.writeStringList(tags)
         parcel.writeString(brand)
         parcel.writeString(sku)
         parcel.writeLong(weight)
         parcel.writeParcelable(dimensions, flags)
         parcel.writeString(warrantyInformation)
         parcel.writeString(shippingInformation)
         parcel.writeString(availabilityStatus)
         parcel.writeTypedList(reviews)
         parcel.writeString(returnPolicy)
         parcel.writeLong(minimumOrderQuantity)
         parcel.writeParcelable(meta, flags)
         parcel.writeStringList(images)
         parcel.writeString(thumbnail)
     }

     override fun describeContents(): Int {
         return 0
     }

     companion object CREATOR : Parcelable.Creator<Product> {
         override fun createFromParcel(parcel: Parcel): Product {
             return Product(parcel)
         }

         override fun newArray(size: Int): Array<Product?> {
             return arrayOfNulls(size)
         }
     }
 }


data class Dimensions(
    val width: Double,
    val height: Double,
    val depth: Double,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble()
    ) {}

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(width)
        parcel.writeDouble(height)
        parcel.writeDouble(depth)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Dimensions> {
        override fun createFromParcel(parcel: Parcel): Dimensions {
            return Dimensions(parcel)
        }

        override fun newArray(size: Int): Array<Dimensions?> {
            return arrayOfNulls(size)
        }
    }
}

data class Review(
    val rating: Long,
    val comment: String,
    val date: String,
    val reviewerName: String,
    val reviewerEmail: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {}

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(rating)
        parcel.writeString(comment)
        parcel.writeString(date)
        parcel.writeString(reviewerName)
        parcel.writeString(reviewerEmail)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Review> {
        override fun createFromParcel(parcel: Parcel): Review {
            return Review(parcel)
        }

        override fun newArray(size: Int): Array<Review?> {
            return arrayOfNulls(size)
        }
    }
}

data class Meta(
    val createdAt: String,
    val updatedAt: String,
    val barcode: String,
    val qrCode: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {}

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
        parcel.writeString(barcode)
        parcel.writeString(qrCode)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Meta> {
        override fun createFromParcel(parcel: Parcel): Meta {
            return Meta(parcel)
        }

        override fun newArray(size: Int): Array<Meta?> {
            return arrayOfNulls(size)
        }
    }
}

