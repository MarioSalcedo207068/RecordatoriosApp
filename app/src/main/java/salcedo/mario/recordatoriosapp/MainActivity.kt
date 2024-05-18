package salcedo.mario.recordatoriosapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class MainActivity : AppCompatActivity() {

    private lateinit var editTextDescription: EditText
    private lateinit var editTextDate: EditText
    private lateinit var buttonRegister: Button
    private lateinit var recyclerViewReminders: RecyclerView

    private lateinit var reminderAdapter: ReminderAdapter
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextDescription = findViewById(R.id.editTextDescription)
        editTextDate = findViewById(R.id.editTextDate)
        buttonRegister = findViewById(R.id.buttonRegister)
        recyclerViewReminders = findViewById(R.id.recyclerViewReminders)

        recyclerViewReminders.layoutManager = LinearLayoutManager(this)

        buttonRegister.setOnClickListener {
            val description = editTextDescription.text.toString()
            val date = editTextDate.text.toString()
            val reminder = Reminder(description, date)
            db.collection("reminders")
                .add(reminder)
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val query = db.collection("reminders").orderBy("date", Query.Direction.ASCENDING)
        val options = FirestoreRecyclerOptions.Builder<Reminder>()
            .setQuery(query, Reminder::class.java)
            .build()

        reminderAdapter = ReminderAdapter(options)
        recyclerViewReminders.adapter = reminderAdapter
    }

    override fun onStart() {
        super.onStart()
        reminderAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        reminderAdapter.stopListening()
    }
}