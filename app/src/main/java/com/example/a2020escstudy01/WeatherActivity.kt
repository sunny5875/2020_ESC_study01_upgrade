package com.example.a2020escstudy01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_weather.*
import org.json.JSONException


class WeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)


        var requestQueue: RequestQueue = Volley.newRequestQueue(this)


        var url =
            "https://api.openweathermap.org/data/2.5/weather?q=Seoul&appid=d2a99d8c48e57f83c9da05064b3fad26"

        //api 호출
        var request = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener {
                //정상적으로 처리되었을 때 처리 코드
                //response : api 호출을 통해 넘어온 데이터
                    response ->
                try {
                    //JSONArray : [] 배열
                    //JSONObject : {} 객체

                    val weatherId: Int =
                        response.getJSONArray("weather").getJSONObject(0).getInt("id")

                    val description: String =
                        response.getJSONArray("weather").getJSONObject(0).getString("description")
                    val temp: Double = response.getJSONObject("main").getDouble("temp")
                    val humidity: Int = response.getJSONObject("main").getInt("humidity")
                    val speed: Double = response.getJSONObject("wind").getDouble("speed")
                    val all: Int = response.getJSONObject("clouds").getInt("all")
                    val name: String = response.getString("name")



                    tv_region.text = name
                    tv_description.text = description
                    tv_temp.text = (temp - 273.15).toInt().toString() + "°C"
                    tv_cloud.text = all.toString() + "%"
                    tv_humidity.text = humidity.toString() + "%"
                    tv_speed.text = speed.toString() + "km/h"

                    //double형이 int형으로 바꾸면 이쁘게 나올 수 있다.
                    //temp.toInt()


/*
* 2xx : 천둥번개 stomy01.jpg
* 3xx : 가랑비-> rainyxx.jpg
* 5xx : 비 ->rainyxx.jpg
* 6xx :눈 ->snowyxx.jpg
* 7xx : 안개 -> cloudyxx.jpg
* 800 : 음->sunnyxx.jpg
* 80X : 구름 ->cloudyxx.jpg
*  */
                    if (weatherId > 200 && weatherId < 300) {
                        imageView.setImageResource(R.drawable.stomy01)
                    } else if (weatherId > 300 && weatherId < 600) {
                        imageView.setImageResource(R.drawable.rainy)
                    } else if (weatherId > 700 && weatherId < 800) {
                        imageView.setImageResource(R.drawable.cloudy)
                    } else if (weatherId == 800) {
                        imageView.setImageResource(R.drawable.sunny)
                    } else if (weatherId > 800 && weatherId < 810) {
                        imageView.setImageResource(R.drawable.cloudy)
                    }


                } catch (e: JSONException) {
                    Toast.makeText(this, e.localizedMessage, Toast.LENGTH_LONG).show()

                }

            },

            Response.ErrorListener {
                //데이터가 비정상적으로 처리되었을 때 처리 코드
                    error ->
                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_LONG).show()

            }
        )

        requestQueue.add(request)

        button.setOnClickListener {
             url =
                "https://api.openweathermap.org/data/2.5/weather?q="+editText.text+"&appid=d2a99d8c48e57f83c9da05064b3fad26"

            //api 호출
            request = JsonObjectRequest(
                Request.Method.GET, url, null,
                Response.Listener {
                    //정상적으로 처리되었을 때 처리 코드
                    //response : api 호출을 통해 넘어온 데이터
                        response ->
                    try {
                        //JSONArray : [] 배열
                        //JSONObject : {} 객체

                        val weatherId: Int =
                            response.getJSONArray("weather").getJSONObject(0).getInt("id")

                        val description: String =
                            response.getJSONArray("weather").getJSONObject(0).getString("description")
                        val temp: Double = response.getJSONObject("main").getDouble("temp")
                        val humidity: Int = response.getJSONObject("main").getInt("humidity")
                        val speed: Double = response.getJSONObject("wind").getDouble("speed")
                        val all: Int = response.getJSONObject("clouds").getInt("all")
                        val name: String = response.getString("name")



                        tv_region.text = name
                        tv_description.text = description
                        tv_temp.text = (temp - 273.15).toInt().toString() + "°C"
                        tv_cloud.text = all.toString() + "%"
                        tv_humidity.text = humidity.toString() + "%"
                        tv_speed.text = speed.toString() + "km/h"

                        //double형이 int형으로 바꾸면 이쁘게 나올 수 있다.
                        //temp.toInt()


/*
* 2xx : 천둥번개 stomy01.jpg
* 3xx : 가랑비-> rainyxx.jpg
* 5xx : 비 ->rainyxx.jpg
* 6xx :눈 ->snowyxx.jpg
* 7xx : 안개 -> cloudyxx.jpg
* 800 : 음->sunnyxx.jpg
* 80X : 구름 ->cloudyxx.jpg
*  */
                        if (weatherId > 200 && weatherId < 300) {
                            imageView.setImageResource(R.drawable.stomy01)
                        } else if (weatherId > 300 && weatherId < 600) {
                            imageView.setImageResource(R.drawable.rainy)
                        } else if (weatherId > 700 && weatherId < 800) {
                            imageView.setImageResource(R.drawable.cloudy)
                        } else if (weatherId == 800) {
                            imageView.setImageResource(R.drawable.sunny)
                        } else if (weatherId > 800 && weatherId < 810) {
                            imageView.setImageResource(R.drawable.cloudy)
                        }


                    } catch (e: JSONException) {
                        Toast.makeText(this, e.localizedMessage, Toast.LENGTH_LONG).show()

                    }

                },

                Response.ErrorListener {
                    //데이터가 비정상적으로 처리되었을 때 처리 코드
                        error ->
                    Toast.makeText(this, error.localizedMessage, Toast.LENGTH_LONG).show()

                }
            )

            requestQueue.add(request)

        }
    }


}
