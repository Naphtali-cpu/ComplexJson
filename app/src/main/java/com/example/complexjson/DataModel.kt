package com.example.complexjson

class DataModel {

    var subject: String? = null
    var description: String? = null
    var city: String? = null
    var imagepath: List<ImageUrl>? = null

    class ImageUrl {
        var url: String? = null

        fun getimageurl(): String {
            return url.toString()
        }

        fun setimageurl(imgurl: String) {
            this.url = imgurl
        }

    }

    fun getNames(): String {
        return subject.toString()
    }

    fun setNames(name: String) {
        this.subject = name
    }

    fun getCountrys(): String {
        return description.toString()
    }

    fun setCountrys(country: String) {
        this.description = country
    }

    fun getCitys(): String {
        return city.toString()
    }

    fun setCitys(city: String) {
        this.city = city
    }

    fun getimgURLs(): List<ImageUrl>? {
        return imagepath
    }

    fun setimgURLs(imgURL: List<ImageUrl>) {
        this.imagepath = imgURL
    }

}