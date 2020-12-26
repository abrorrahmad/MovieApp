package com.abrorrahmad.movieapp.sign.signup

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.abrorrahmad.movieapp.home.HomeActivity
import com.abrorrahmad.movieapp.R
import com.abrorrahmad.movieapp.utils.Preferences
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_sign_up_photoscreen.*
import java.util.*


class SignUpPhotoscreenActivity : AppCompatActivity(), PermissionListener {

    val REQUEST_IMAGE_PICTURE = 1
    var statusADD:Boolean = false
    lateinit var filePath: Uri

    lateinit var storage : FirebaseStorage
    lateinit var storageReferensi : StorageReference
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_photoscreen)

        preferences = Preferences(this)
        storage = FirebaseStorage.getInstance()
        storageReferensi = storage.getReference()

        tv_hello.text = "Selamat Datang\n"+intent.getStringExtra("nama")

        iv_add.setOnClickListener {
            if (statusADD){
                statusADD = false
                btn_save.visibility = View.VISIBLE
                iv_add.setImageResource(R.drawable.ic_add)
                iv_profile.setImageResource(R.drawable.photo)
            }else{
                Dexter.withActivity(this)
                    .withPermission(android.Manifest.permission.CAMERA)
                    .withListener(this)
                    .check()
            }
        }

        btn_home.setOnClickListener {
            finishAffinity()

            var goHome = Intent(this@SignUpPhotoscreenActivity, HomeActivity::class.java)
            startActivity(goHome)
        }
        btn_save.setOnClickListener {
            if (filePath != null){

                var progressDialog = ProgressDialog(this)
                progressDialog.setTitle("Uploading..")
                progressDialog.show()

                var ref = storageReferensi.child("images/"+UUID.randomUUID().toString())
                ref.putFile(filePath)
                    .addOnSuccessListener {
                        progressDialog.dismiss()
                        Toast.makeText(this,"uploaded", Toast.LENGTH_LONG).show()

                        ref.downloadUrl.addOnSuccessListener {
                            preferences.setValues("url", it.toString())
                        }
                        finishAffinity()

                        var goHome = Intent(this@SignUpPhotoscreenActivity, HomeActivity::class.java)
                        startActivity(goHome)
                    }
                    .addOnFailureListener{
                        progressDialog.dismiss()
                        Toast.makeText(this,"Failed", Toast.LENGTH_LONG).show()
                    }
                    .addOnProgressListener {
                        taskSnapshot -> var progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                        progressDialog.setMessage("Upload" +progress.toInt()+"% ")
                    }

            }else{


            }
        }
    }

    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
       Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
           takePictureIntent ->
           takePictureIntent.resolveActivity(packageManager)?.also {
               startActivityForResult(takePictureIntent, REQUEST_IMAGE_PICTURE)
           }
       }
    }


    override fun onPermissionRationaleShouldBeShown(
        permission: PermissionRequest?,
        token: PermissionToken?
    ) {

    }
    override fun onPermissionDenied(response: PermissionDeniedResponse?) {

        Toast.makeText(this,"Anda Tidak Bisa Menambahkan Photo Profile", Toast.LENGTH_LONG).show()

    }

    override fun onBackPressed() {

        Toast.makeText(this,"Tergesah ? Klik Tombol upload Nanti Aja", Toast.LENGTH_LONG).show()

    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_PICTURE && resultCode == Activity.RESULT_OK){
            var bitmap = data?.extras?.get("data")as Bitmap
            statusADD = true

            filePath = data.getData()!!
            Glide.with(this)
                .load(bitmap)
                .apply(RequestOptions.circleCropTransform())
                .into(iv_profile)

            btn_save.visibility = View.VISIBLE
            iv_add.setImageResource(R.drawable.ic_btn_delete)
        }
    }

}