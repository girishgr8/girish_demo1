package com.example.android.chat_demo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;

import java.io.IOException;

public class ScrapedPage extends AppCompatActivity {
TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scraped_page);
         text=findViewById(R.id.text);
        text.setMovementMethod(new ScrollingMovementMethod());
        Button but = findViewById(R.id.btn);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new doit().execute();
            }
        });


    }



    public  class doit extends AsyncTask<Void,Void,Void> {
        String words;
        Elements price;
        Elements ref;
        StringBuilder b = new StringBuilder();
        @Override
        protected Void doInBackground(Void... voids) {

            try {
                //org.jsoup.nodes.Document doc = Jsoup.connect("https://www.indgovtjobs.in/2015/10/Government-Jobs.html").get();
                org.jsoup.nodes.Document doc = Jsoup.connect("https://www.google.com/search?hl=en&tbm=shop&psb=1&q=Mobile+Phones&tbas=0&tbs=vw:l,mr:1,p_ord:rv&sa=X&ved=0ahUKEwj0rISFifvgAhUWTY8KHftcD7gQvSsI9AIoAA&biw=1366&bih=665").get();
               words=doc.text();
               String title=doc.title();
               //Elements links=doc.select("a[href]");
               Elements links=doc.getElementsByClass("eIuuYe");
                Elements price=doc.getElementsByClass("O8U6h");
               int i=0;
               b.append("Mobile Phones").append("\n");
               for(Element link: links)
               {

                   b.append("\n\nPRICE:  ").append(price.get(i).text());
                   i++;
                   b.append("\n").append("\n").append("PRODUCT:  ").append(link.text()).append("\n\nVISIT:  ").append(links.select("a[href]").attr("href")).append(link.attr("href")).append("\n");
               }
               // price = doc.select(".zsg-photo-card-price:contains($)");
                ref=doc.getElementsByClass("strong");
               // System.out.println("ref");
                //ref=doc.select("img");
                //Log.d("CODE","Image = "+ref.get(0).attr("src"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            text.setText(b.toString());


            /*for (Element link : ref)
            {
                //view.setText((CharSequence) link);
                text.setText(link.toString());
                //System.out.println("HELLO "+ link.attr("abs:href"));
               // Log.d("CODE","Image = "+link.attr("abs:href"));
            }*/


            }

        }
    }

