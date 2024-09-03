package com.example.hospitalmanagementsystem.ui.common.tasks.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.hospitalmanagementsystem.R
import com.example.hospitalmanagementsystem.databinding.FragmentTaskDetailsBinding
import com.example.hospitalmanagementsystem.ui.common.tasks.presentation.adapter.AdapterRecyclerToDo
import com.example.hospitalmanagementsystem.utils.Const
import com.example.hospitalmanagementsystem.utils.MySharedPreferences
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskDetailsFragment : Fragment() {
    private var _binding: FragmentTaskDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TasksViewModel by viewModels()
    private val adapter = AdapterRecyclerToDo()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(R.layout.fragment_task_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTaskDetailsBinding.bind(view)
        val id = TaskDetailsFragmentArgs.fromBundle(requireArguments()).id
        onClick(id)
        showData(id)
        observe()
        initView()
    }

    private fun initView() {
        binding.apply {
            if (MySharedPreferences.getUserType() == Const.MANAGER) {
                btnExecution.visibility = View.GONE
                editNoteTask.visibility = View.GONE
            }
        }
    }

    private fun onClick(id: Int) {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }
            btnExecution.setOnClickListener {
                viewModel.execution(id, editNoteTask.text.toString())
            }
        }


    }

    private fun showData(id: Int) {
        viewModel.showTaskDetails(id)
    }

    fun observe() {
        viewModel.taskDetails.observe(viewLifecycleOwner) {
            val data = it.data
            binding.apply {

                data.apply {
                    textTaskName.text = task_name
                    textDate.text = created_at
                    textUserName.text = user.first_name + " " + user.last_name
                    textType.text = "Specialist - ${user.specialist}"
                    if (MySharedPreferences.getUserType() == Const.MANAGER) {
                        textDetails.text = note
                    } else {
                        textDetails.text = description
                    }

                    adapter.toDo = to_do
                    recyclerTodo.adapter = adapter
                }
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}