package com.suzume.movies.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.suzume.movies.R
import com.suzume.movies.data.pojo.movieDetailResponse.Person
import com.suzume.movies.databinding.ActivityPersonListBinding
import com.suzume.movies.presentation.adapter.personListScreen.PersonListScreenAdapter

class PersonListActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_PERSONS = "persons"
        private const val EXTRA_PROFESSION = "profession"
        const val ACTOR = "Актёр"
        const val MOVIE_TEAM = "Съёмочная группа"
        fun getIntent(context: Context, persons: ArrayList<Person>, profession: String): Intent {
            return Intent(context, PersonListActivity::class.java)
                .putParcelableArrayListExtra(EXTRA_PERSONS, persons)
                .putExtra(EXTRA_PROFESSION, profession)
        }
    }

    private lateinit var binding: ActivityPersonListBinding
    private lateinit var persons: List<Person>
    private lateinit var label: String
    private lateinit var adapter: PersonListScreenAdapter
    private var searchTextLenght = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityPersonListBinding.inflate(layoutInflater).also { setContentView(it.root) }

        init()

    }

    private fun init() {
        persons = intent.getParcelableArrayListExtra<Person>(EXTRA_PERSONS)!!
        label = intent.getStringExtra(EXTRA_PROFESSION).toString()
        binding.tvLabel.text = label
        adapter = PersonListScreenAdapter()
        adapter.submitList(persons)
        binding.rvPersonList.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val menuItem = menu?.findItem(R.id.menuItemSearch)
        val searchView = menuItem?.actionView as SearchView
        searchView.queryHint = "Enter name..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val tempList = mutableListOf<Person>()
                if (newText == null || newText.isEmpty()) {
                    tempList.addAll(persons)
                } else {
                    val filterPattern = newText.lowercase()
                    tempList.addAll(
                        persons.filter { it.name?.lowercase()?.contains(filterPattern) == true }
                    )
                    tempList.addAll(
                        persons.filter {
                            it.description?.lowercase()?.contains(filterPattern) == true
                        }
                    )
                }
                adapter.submitList(tempList)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

}