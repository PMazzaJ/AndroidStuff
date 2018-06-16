package com.example.android.movieproject.Model;

import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("author")
    private String author;
    @SerializedName("content")
    private String content;


    public Review() {
    }

    public Review(String author, String content) {
        this.author = author;
        this.content = content;

    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }



    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }


}

/*     "author": "garethmb",
      "content": "The Mercenary with a mouth is back with the eagerly awaited arrival of “Deadpool 2”.
      The films sees the generally well-meaning but highly dysfunctional Deadpool (Ryan Reynolds), back to taking out bad guys as a contract player but also managing
       his relationship with Vanessa (Morena Baccarin).  When an unexpected event sends his life into a freefall, Deadpool tries to find a new purpose with the help of his X-Men associates from the last film who attempt to recruit him into their organization.\nHis first mission is to defuse an angry and destructive young mutant named Russell (Julian Dennison), which takes an unexpected turn and lands them both in serious trouble.\n\nOne would think that would be enough to cause some major life changes for Deadpool but thanks to the arrival of Cable (Josh Brolin), a cyborg soldier from the future; things are about to get much more complicated and intense.\nUndaunted, Deadpool opts to form his own league of heroes and aside from Domino (Zazie Beetz); they seem to be as unlucky or dysfunctional as their leader which makes for some very hysterical consequences.\n\nWhat follows is an action and laugh-laden adventure which brings even more of what made the first film such a success to the audience as the film takes the bawdy action of the first and ups the ante thanks in large part to an expanded budget and cast.\nMy biggest concern for the film was that with an expanded budget there would be too many characters and an attempt to do far too much with the film. That did play out at times in the beginning as for me, the first film worked so well as they had to let the characters rather than the action and effects carry it and the rapid-fire arrival of so many jokes and creative profanity made repeat viewing of the film necessary to catch everything.\n\nThis time out we get elaborate action and chase sequences as well as a much larger cast. At times it seemed as if this would possibly overshadow the characters and story but Reynolds and Director David Leith never let it cross that line.\nThey also go back to the core elements in the final third of the film which really allows the film to fully connect with the tone of the original film and brings the film home to a satisfying conclusion.  What really sold the film for me was the brilliant and very inspired extended scene during the credits which allows Deadpool to “fix” various issues which perfectly captures the irreverent character and the best aspects of the series where the cast is willing to make fun of themselves as well as the larger universe in which their characters exist.\n\nI cannot wait to see what comes next as “DeadPool 2” is another fun outing for the character and something very different from most comic based movies.\n\n4 stars out of 5",
      "id": "5afa5a93925141414b005cf0",
      "url": "https://www.themoviedb.org/review/5afa5a93925141414b005cf0"

      */