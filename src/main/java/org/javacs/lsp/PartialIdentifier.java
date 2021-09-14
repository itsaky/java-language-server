package org.javacs.lsp;

import com.google.gson.annotations.SerializedName;

public class PartialIdentifier {
    
    @SerializedName("name")
    public String name;
    
    @SerializedName("start")
    public int start;
    
    @SerializedName("end")
    public int end;

    public PartialIdentifier (String name, int start, int end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }
}
