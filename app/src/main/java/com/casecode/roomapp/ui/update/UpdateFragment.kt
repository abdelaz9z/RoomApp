package com.casecode.roomapp.ui.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
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
import androidx.navigation.fragment.navArgs
import com.casecode.domain.entity.User
import com.casecode.roomapp.R
import com.casecode.roomapp.databinding.FragmentUpdateBinding
import com.casecode.roomapp.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding
    private val userViewModel: UserViewModel by viewModels()

    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(inflater, container, false)

        binding.editTextFirstName.setText(args.currentUser.firstName)
        binding.editTextLastName.setText(args.currentUser.lastName)
        binding.editTextAge.setText(args.currentUser.age.toString())

        binding.buttonUpdate.setOnClickListener {
            updateItem()
        }

        // add menu
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun updateItem() {
        val firstName = binding.editTextFirstName.text.toString()
        val lastName = binding.editTextLastName.text.toString()
        val age = Integer.parseInt(binding.editTextAge.text.toString())

        if (inputCheck(firstName, lastName, binding.editTextAge.text)) {
            // create user object
            val user = User(id = args.currentUser.id, firstName, lastName, age)
            // update current user
            userViewModel.updateUser(user)
            Toast.makeText(
                requireContext(),
                "Updated successfully!",
                Toast.LENGTH_LONG
            ).show()
            // navigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(
                requireContext(),
                getString(R.string.please_fill_out_all_fields),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage(
            getString(
                R.string.are_you_sure_you_want_to_delete,
                args.currentUser.firstName
            )
        )
        builder.setPositiveButton(getString(R.string.yes)) { _, _ ->
            userViewModel.deleteUser(args.currentUser)
            Toast.makeText(
                requireContext(),
                getString(R.string.successfully_removed, args.currentUser.firstName),
                Toast.LENGTH_SHORT
            ).show()
            // navigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton(getString(R.string.no)) { _, _ -> }
        builder.create().show()
    }
}