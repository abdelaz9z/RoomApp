package com.casecode.roomapp.ui.list

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.casecode.roomapp.R
import com.casecode.roomapp.adapter.ListAdapter
import com.casecode.roomapp.databinding.FragmentListBinding
import com.casecode.roomapp.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var listAdapter: ListAdapter

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)

        // move to add fragment
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        // Setting up items details RecyclerView
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.setHasFixedSize(true)

        userViewModel.readAllData.observe(viewLifecycleOwner) { user ->
            // Instantiating ItemsDetailsAdapter & passing
            // lambda function in its constructor
            listAdapter = ListAdapter(user) { user ->
                // Here we'll receive a callback of
                // every RecyclerView item click
                // Now, perform any action here.
                // for ex: navigate to a different screen
                Log.i("TAG", "sheetItemsDetails: $user")
                val action =
                    ListFragmentDirections.actionListFragmentToUpdateFragment(
                        user
                    )
                findNavController().navigate(action)
            }
            binding.recycler.adapter = listAdapter // Move the adapter assignment here
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        // add menu
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.delete_everything))
        builder.setMessage(getString(R.string.are_you_sure_you_want_to_delete_everything))
        builder.setPositiveButton(getString(R.string.yes)) { _, _ ->
            userViewModel.deleteAllUsers()
            Toast.makeText(
                requireContext(),
                getString(R.string.successfully_removed_everything),
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton(getString(R.string.no)) { _, _ -> }
        builder.create().show()
    }

}