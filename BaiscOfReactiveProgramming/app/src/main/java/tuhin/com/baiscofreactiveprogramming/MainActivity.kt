package tuhin.com.baiscofreactiveprogramming

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import io.reactivex.subjects.BehaviorSubject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testImperative()
        testReactive()
    }

    private fun testImperative() {

        /*Example of imperative programming*/

        println("=======================Imperative programming Start================")

        var b = 1
        var c = 2
        var a = b + c

        //printing the state of 'a'
        println("The initial value of 'a' is : $a")
        //value of 'a'  is 3

        //Updating the value (state) of c
        c = 3

        //printing the state of 'a' again after changing the state of 'c'
        println("The value of 'a' after changing 'c' is :  $a")

        //Output : The value of 'a' is still 3
        // as we don't perform the operation {a = b + c} again

        /*So, we can conclude that even though the state of c is changed the state of a is not changed*/

        /* If we want the value of 'a' is 4 then we have to do it in a reactive way

        That means, if we want to achieve that whenever the state of 'c' will be changed the state of
        'a' will be changed  as well then the reactive programming will have to come into picture
         */
        println("=======================Imperative programming End================")
    }

    private fun testReactive() {
        /*Example of reactive programming*/

        println("=======================Reactive programming Start================")


        var b = 1
        var c = BehaviorSubject.createDefault(2) // same operation {c = 2} in imperative programming
        var a = 0
        c.subscribe{ c-> a = b + c } //same operation {a = b + c} in imperative programming
        println("The initial value of 'a' is : $a")
        //Output of 'a' is 3

        //Updating the value (state) of 'c'
        c.onNext(3) // same operation {c = 3} in imperative programming

        //printing the state of 'a' again after changing the state of 'c'
        println("The  value of 'a' after changing 'c' is :  $a")

        //The value of 'a' is now 4 without performing the operation {a = b + c} further

        /*
        We can conclude that whenever the state of something will be changed it will be notified to others depending on it
        and their state will be changed as well
         */

        println("=======================Reactive programming End================")
    }

}
