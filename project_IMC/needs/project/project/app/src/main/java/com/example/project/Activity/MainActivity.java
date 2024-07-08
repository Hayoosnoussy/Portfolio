package com.example.project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.project.Adapter.PopularAdapter;
import com.example.project.Domain.InstrumentDomain;
import com.example.project.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewPopular();
        bottomNavigation();
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.card_btn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CartListActivity.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
    }

    private void recyclerViewPopular() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<InstrumentDomain> instrumentlist = new ArrayList<>();
        instrumentlist.add(new InstrumentDomain("KRK Rokit MONITORS", "krkmonitors", "The new 5 KRK ROKIT RP5 G4 (Generation 4) bi-amp professional monitor takes music and sound creativity to a whole new industry-level.\n", 1099.99));
        instrumentlist.add(new InstrumentDomain("Akai Mini Mk2", "akaimpkmini", "The ultra-portable MPK Mini Play is based on the world’s most popular midi controller—Akai Professional’s MPK Mini—but now it comes packed with 128 sounds and its own built-in speaker.\n", 199.99));
        instrumentlist.add(new InstrumentDomain("Sound-Card", "carteson", " The M-Track Duo is the best, most portable recording interface in its class. It delivers 48 kHz audio resolution in a compact and easy to use format that is ideal for creating music on any Mac, PC or iOS device. \n", 300.9));
        instrumentlist.add(new InstrumentDomain("Guitar", "guitar", "Tenson electric bass guitars Mercury series take the guesswork out of finding an affordable, great-sounding bass that's easy to fret and play. These guitars are extremely well-constructed, affordable and have the pristine tonality and playability.\n", 389.5));
        instrumentlist.add(new InstrumentDomain("Akai MPC", "akaimpc", "AKAI MPC with some sample packs included for producing and deejaying\n ", 299.99));
        instrumentlist.add(new InstrumentDomain("Piano", "piano", "88-key compensated model with touch response. Keyboard with cover. 32-note polyphony, 128 tones. 203 rhythm styles. Real-time recording and playback .\n ", 599.99));
        instrumentlist.add(new InstrumentDomain("Piano Classic", "pianokeyscape", "If you’re learning piano as a new hobby or just looking to start playing again, you want to pick an instrument that matches your style. \n ", 1599.99));

        instrumentlist.add(new InstrumentDomain("Violin Strings", "violin", "Expect a violin of this level to literally come apart at the seams and develop cracks as the wood dries out. The neck may even fall out! Because they are mass-produced, these violins often have thin, shrill tones. They lack a proper setup, making them actually much harder for a beginner to play.\n ", 599.99));


        adapter2 = new PopularAdapter(instrumentlist);
        recyclerViewPopularList.setAdapter(adapter2);

    }


}