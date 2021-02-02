package com.seloger.android.estateviewer.ui.gallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import com.seloger.android.estateviewer.R
import com.seloger.android.estateviewer.data.entity.Estate
import com.seloger.android.estateviewer.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    private val viewModel by viewModels<GalleryViewModel>()
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentGalleryBinding.bind(view)

        val adapter = EstatesListAdapter { estate ->
            navigate(estate)
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)

        viewModel.estates.observe(viewLifecycleOwner) { estates ->
            adapter.submitList(estates)
        }
    }

    private fun navigate(estate: Estate) {
        val action =
            GalleryFragmentDirections.actionGalleryFragmentToDetailsFragment(estate)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}