package com.example.catatan10120085.dialog

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.catatan10120085.OnClickItemListener
import com.example.catatan10120085.database.AppDatabase
import com.example.catatan10120085.database.Note
import com.example.catatan10120085.databinding.DialogDetailCatatanBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
//10120085 - Dion Cahyan IF-2
class DetailCatatanDialog(var database: AppDatabase, var detailNote: Note?, val listener: OnClickItemListener): DialogFragment() {
    private lateinit var binding : DialogDetailCatatanBinding
    private lateinit var note: Note
    private var isEdit = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogDetailCatatanBinding.inflate(layoutInflater)

        binding.btnSave.setOnClickListener {
            saveData()
        }

        binding.btnBack.setOnClickListener {
           dismiss()
        }
        if (detailNote != null){
            isEdit = true
            binding.etNoteTitle.setText(detailNote!!.judul)
            binding.etCategory.setText(detailNote!!.kategori)
            binding.etNoteBody.setText(detailNote!!.note)
        }


        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    private fun saveData() {
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val current = formatter.format(time)
        if (binding.etNoteTitle.text.isNotEmpty() && binding.etCategory.text.isNotEmpty() && binding.etNoteBody.text.isNotEmpty()) {
            if (isEdit) {
                lifecycleScope.launch(Dispatchers.IO) {
                    detailNote!!.judul = binding.etNoteTitle.text.toString().trim()
                    detailNote!!.kategori = binding.etCategory.text.toString().trim()
                    detailNote!!.note = binding.etNoteBody.text.toString().trim()
                    detailNote!!.date = current
                    database.dataDao().updateNote(detailNote!!)

                    requireActivity().runOnUiThread{

                        Toast.makeText(requireContext(),"Berhasil merubah Catatan :)", Toast.LENGTH_SHORT).show()

                        dismiss()
                        listener.onUpdateNote()
                    }
                }
            } else {
                lifecycleScope.launch(Dispatchers.IO) {
                    note = Note(
                        0,
                        current,
                        binding.etNoteTitle.text.toString().trim(),
                        binding.etCategory.text.toString().trim(),
                        binding.etNoteBody.text.toString().trim()
                    )
                    database.dataDao().insertNote(note)

                }

                Toast.makeText(requireContext(),"Berhasil menyimpan Catatan :)", Toast.LENGTH_SHORT).show()

                dismiss()
                listener.onUpdateNote()
            }

        } else {
            Toast.makeText(requireContext(),"Judul, Kategory, dan catatan tidak boleh kosong!!!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            it.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT ,ViewGroup.LayoutParams.MATCH_PARENT )
        }
    }
}