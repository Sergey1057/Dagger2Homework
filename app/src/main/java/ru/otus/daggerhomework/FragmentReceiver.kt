package ru.otus.daggerhomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import javax.inject.Inject

class FragmentReceiver : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelReceiver.ViewModelReceiverFactory

    private val viewModel: ViewModelReceiver by viewModels { viewModelFactory }
    private lateinit var frame: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerFragmentReceiverComponent.factory()
            .create((requireActivity() as MainActivity).mainActivityComponent)
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        frame = view.findViewById(R.id.frame)
        viewModel.color.observe(viewLifecycleOwner) {
            populateColor(it)
        }
    }

    fun populateColor(@ColorInt color: Int) {
        frame.setBackgroundColor(color)
    }
}