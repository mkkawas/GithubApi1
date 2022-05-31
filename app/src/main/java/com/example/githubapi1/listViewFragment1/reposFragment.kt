package com.example.githubapi1.listViewFragment1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapi1.R
import com.example.githubapi1.adapters.ListLoadStateAdapter
import com.example.githubapi1.paging.MainListAdapter
import com.example.githubapi1.databinding.FragmentReposBinding
import com.example.githubapi1.modelsKoosa.GetReposModel
import kotlinx.coroutines.flow.collectLatest

class reposFragment : Fragment(R.layout.fragment_repos) {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var mainListAdapter: MainListAdapter
    private lateinit var binding: FragmentReposBinding
    private lateinit var dataFromViewModel: ArrayList<PagingData<GetReposModel>>
    var count = 1
    var flag = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReposBinding.inflate(inflater, container, false)


        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)


        dataFromViewModel = arrayListOf()
        setupList()

        addDataToRecycler()


        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
//            mainListAdapter.withLoadStateFooter()
            binding.recycler.visibility = View.GONE
            mainListAdapter.refresh()
            binding.recycler.visibility = View.VISIBLE

//            Toast.makeText(context, "Data Refreshed", Toast.LENGTH_SHORT).show()
        }


        // setupView()


//        Toast.makeText(context, "koosa", Toast.LENGTH_LONG ).show()


    }

    private fun addDataToRecycler() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.listData.collectLatest {


                Log.d("Output Frg", it.toString())
                if (!dataFromViewModel.contains(it)) {
                    dataFromViewModel.add(it)
                    mainListAdapter.submitData(it)
                }
            }
        }
    }

    private fun setupList() {
        mainListAdapter = MainListAdapter()
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mainListAdapter
                .withLoadStateFooter(
                    footer = ListLoadStateAdapter {
                        mainListAdapter.retry()
                    }
                )

        }
        mainListAdapter.addLoadStateListener { loadState ->

            if (loadState.refresh is LoadState.Loading && !flag) {
                binding.progressBar.visibility = View.VISIBLE
                flag = true
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

    }

//    private fun setupViewModel() {
//        viewModel =
//            ViewModelProvider(
//                this,
//                MainViewModelFactory()
//            )[MainViewModel::class.java]
//
//
//    }

//    private  fun setupRefresh(){
//        binding.swipeRefreshLayout.setOnRefreshListener {
//            setupList()
//            setupViewModel()
//            addDataToRecycler()
//        }
//    }

//    private fun fetchDataFromApi(): List<GetReposModel> {
//        var list: List<GetReposModel>? = null
//
//        viewLifecycleOwner.lifecycleScope.launch{
//            RetrofitClient.instance.getRepos(count).enqueue(
//                object : Callback<List<GetReposModel>> {
//                    override fun onResponse(
//                        call: Call<List<GetReposModel>>,
//                        response: Response<List<GetReposModel>>
//                    ) {
//                        list = response.body()!!
//                    }
//
//                    override fun onFailure(call: Call<List<GetReposModel>>, t: Throwable) {
//                        Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
//                    }
//
//                }
//            )
//        }
//
//
//        return list!!
//
//    }


}