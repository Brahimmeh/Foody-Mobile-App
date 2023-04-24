package com.example.fooddelivery


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

   private lateinit var recyclerView: RecyclerView
   private lateinit var foodList:ArrayList<Foods>
   private lateinit var foodAdaptor: FoodAdaptor

    private lateinit var recyclerView2: RecyclerView
    private lateinit var popularList:ArrayList<Popular>
    private lateinit var popularAdaptor: PopularAdaptor

    private lateinit var auth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth= FirebaseAuth.getInstance()
        val current= auth.currentUser

        val email = current?.email
        var Familyname = current?.displayName
        var img= current?.photoUrl
        val profpic: ImageView = findViewById(R.id.imageView)

        if(Familyname.isNullOrBlank())
        {
            val ref = FirebaseDatabase.getInstance().getReference("user")
            val uid= auth.currentUser?.uid

            ref.child(uid.toString()).get().addOnSuccessListener {
                if(it.exists()){
                val nam = it.child("name").value
                Familyname= nam.toString()
                findViewById<TextView>(R.id.textView3).text= "Welcome " + Familyname
                    findViewById<ImageView>(R.id.imageView).setImageResource(R.drawable.admin_user_icon)
                }
            }
        }
       else{
            findViewById<TextView>(R.id.textView3).text= "Welcome " + Familyname
            Picasso.with(this).load(img).into(profpic)
        }

        if(img == null)
        {
            val ref = FirebaseDatabase.getInstance().getReference("user")
            val uid= auth.currentUser?.uid

            ref.child(uid.toString()).get().addOnSuccessListener {
                if(it.exists()){
                    val im = it.child("image").value.toString()
                    Picasso.with(this).load(im).rotate(90F).into(profpic)
                }}
        }
        else
        {
            Picasso.with(this).load(img).into(profpic)
        }

        startList()
        startPopular()

        popularAdaptor.onItemClick = {
            val intent = Intent(this, DetailsPage::class.java)
            intent.putExtra("food",it)
            startActivity(intent)
        }
        val shoppingCard: FloatingActionButton = findViewById(R.id.shop)

        shoppingCard.setOnClickListener {
            val intent = Intent(this, ShoppingCard::class.java)
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.imageView3).setOnClickListener {
            val intent = Intent(this, Profil::class.java)
            startActivity(intent)

        }


    }


    private fun startPopular() {
        recyclerView2=findViewById(R.id.Res2)
        recyclerView2.setHasFixedSize(true)
        recyclerView2.layoutManager=LinearLayoutManager(this, RecyclerView.HORIZONTAL , false)
        popularList= ArrayList()

        AddDatatoPopular()

        popularAdaptor= PopularAdaptor(popularList)
        recyclerView2.adapter = popularAdaptor

    }

    private fun startList() {
        recyclerView=findViewById(R.id.Res1)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager= LinearLayoutManager(this, RecyclerView.HORIZONTAL , false)
        foodList= ArrayList()

        addDatatoFoodList()

        foodAdaptor=FoodAdaptor(foodList)
        recyclerView.adapter= foodAdaptor
    }

private fun addDatatoFoodList()
{
    foodList.add(Foods(R.drawable.moroccan_cuisine,"Moroccan"))
    foodList.add(Foods(R.drawable.hamburger,"Burgers"))
    foodList.add(Foods(R.drawable.kebab,"Kebab"))
    foodList.add(Foods(R.drawable.pizza,"Pizza"))
    foodList.add(Foods(R.drawable.sushi_roll,"Asian"))
    foodList.add(Foods(R.drawable.sandwich,"Sandwiches"))
    foodList.add(Foods(R.drawable.ice_cream,"Ice Cream"))
    foodList.add(Foods(R.drawable.donut,"Sweets"))
    foodList.add(Foods(R.drawable.taco,"Tacos"))
    foodList.add(Foods(R.drawable.fried_chicken,"Chicken"))
    foodList.add(Foods(R.drawable.fried_potatoes,"Fries"))
    foodList.add(Foods(R.drawable.snack,"Snacks"))
    foodList.add(Foods(R.drawable.drink,"Drinks"))

}

    private fun AddDatatoPopular() {

        popularList.add(Popular("Tajine", R.drawable.tajine__1_, "80","Meat Olives Potato"))
        popularList.add(Popular("American Burger", R.drawable.burger, "60", "MincedMeat Bun"))
        popularList.add(Popular("Veggie Pizza", R.drawable.veggiepizza, "85","Peppers"))
        popularList.add(Popular("Couscous", R.drawable.cous, "120","Couscous"))
        popularList.add(Popular("HotDog", R.drawable.hotdog, "55","Meat HotDog"))
        popularList.add(Popular("Chicken Kebab", R.drawable.chickenkebab, "90","Cucumber Tomato"))
        popularList.add(Popular("Pepperoni Pizza", R.drawable.pepperonipizza, "150","Pepperoni BlackOlives"))
        popularList.add(Popular("Tacos Mix", R.drawable.tacosmix, "45","Fries"))
        popularList.add(Popular("Cheese Burger", R.drawable.cheeseburger, "50","Fries"))
        popularList.add(Popular("Sushi 9Pieces", R.drawable.mix9psushi, "250","Fries"))
        popularList.add(Popular("Grilled Wings", R.drawable.chikwing, "190","Fries"))
        popularList.add(Popular("Lamb Kebab", R.drawable.lambkebab, "100","Fries"))
        popularList.add(Popular("Turkey Sandwich", R.drawable.sand2, "65","Fries"))
        popularList.add(Popular("Oreo Cake", R.drawable.oreo, "230","Fries"))
        popularList.add(Popular("Ben&Jerry's", R.drawable.chunky, "70","Fries"))
        popularList.add(Popular("Shanghai Noodles", R.drawable.shangainoodles, "180","Fries"))
        popularList.add(Popular("Tacos Meat", R.drawable.meattacos, "50","Fries"))
        popularList.add(Popular("Red Velvet", R.drawable.redvelvet, "330","Fries"))
        popularList.add(Popular("Fried Legs", R.drawable.chikleg, "240","Fries"))
        popularList.add(Popular("Oven Fries", R.drawable.bakedfries, "35","Fries"))
        popularList.add(Popular("Chili Fries", R.drawable.chilicheesefries, "45","Fries"))
        popularList.add(Popular("Natural Jus", R.drawable.juices, "45","Fries"))
        popularList.add(Popular("Pringles", R.drawable.pringles, "25","Fries"))
        popularList.add(Popular("Kitkat Mcflurry", R.drawable.kitkatmcfl, "30","Fries"))
        popularList.add(Popular("Soda", R.drawable.soda, "20","Fries"))
        popularList.add(Popular("Snickers", R.drawable.snickers, "10","Fries"))




    }
}


