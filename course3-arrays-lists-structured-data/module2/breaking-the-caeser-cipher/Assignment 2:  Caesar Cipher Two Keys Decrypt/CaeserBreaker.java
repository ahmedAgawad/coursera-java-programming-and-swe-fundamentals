
/**
 * Write a description of CaeserBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaeserBreaker {
// methods implementation
public int[] countLetters(String message) {
    String alpha = "abcdefghijklmnopqrstuvwxyz";
    int[] counts = new int[26];
    for(int i = 0; i < message.length(); i++) {
        char ch = Character.toLowerCase(message.charAt(i));
        int dex = alpha.indexOf(ch);
        if(dex != -1) {
            counts[dex]++;
        }
    }
    
    return counts;
}


public int maxIndex(int [] vals) {
    int maxDex = 0;
    for(int i = 1; i < vals.length; i++) {
        if(vals[i] > vals[maxDex]) {
            maxDex = i;
        }
    }
    
    return maxDex;
}


public String decrypt(String encrypted) {
    CaesarCipher cc = new CaesarCipher();
    int [] freqs = countLetters(encrypted);
    int maxDex = maxIndex(freqs);
    int dkey = maxDex - 4;
    if(maxDex < 4) {
        dkey = 26 - (4 - maxDex);
    }
    
    return cc.encrypt(encrypted, 26 - dkey);
}


public String halfOfString(String message, int start) {
    StringBuilder sb = new StringBuilder();
    for(int i = start; i < message.length(); i+=2) {
        sb.append(message.charAt(i));
    }
    return sb.toString();
}

public int getKey(String s) {
    int [] count = countLetters(s);
    int maxInd = maxIndex(count);
    
    int dkey = maxInd - 4;
    if(maxInd < 4) {
        dkey = 26 - (4 - maxInd);
    }
    return dkey;
}


public String decryptTwoKeys(String encrypted) {
    String halfOfString0 = halfOfString(encrypted, 0);
    String halfOfString1 = halfOfString(encrypted, 1);
    int keyOfHalf0 = getKey(halfOfString0);
    int keyOfHalf1 = getKey(halfOfString1);
    System.out.println(keyOfHalf0 + "  " + keyOfHalf1);
    CaesarCipher cc = new CaesarCipher();
    String decryptedStr = cc.encryptTwoKeys(encrypted, 26 - keyOfHalf0, 26 - keyOfHalf1);
    return decryptedStr;
    
    
} 

// methods testing

public void testCountLetters() {
    int [] counts = countLetters("aaabbbcccdddeee");
    String alpha = "abcdefghijklmnopqrstuvwxyz";
    for(int i = 0; i < counts.length; i++) {
          System.out.println(alpha.charAt(i) + " reptead " + counts[i] + " times" );
    }
}


public void testMaxIndex() {
    int [] counts = countLetters("aaaaaabbggggggggcde");
    String alpha = "abcdefghijklmnopqrstuvwxyz";
    int mostFreq = maxIndex(counts);
    System.out.println(alpha.charAt(mostFreq) + " repeated " + counts[mostFreq] + " times (most freq)");
}


public void testDecrypt() {
   String message = "COBB ZXHB FK QEB ZLKCBOBKZB OLLJ!";
   System.out.println("Decrypted message = " + decrypt(message));
    
}


public void testHalfOfString() {
    System.out.println(halfOfString("Qbkm Zgis", 0));
    System.out.println(halfOfString("Qbkm Zgis", 1));
}

public void testDecryptTwoKeys() {
     //String message = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
    // System.out.println("Decrypted message = " + decryptTwoKeys(message)); 
        
     //message = "Io iwjv jz dv bcm kjvammmikz mwju edbc twpz pvb wi awm v ncmxmqnm xvzog. TMZMZMZMZMZMZMGT TJCY!";
     //System.out.println("Decrypted message = " + decryptTwoKeys(message));
        
    String message = "Xifqvximt tsdtlxzrx iijirvtl ek Uybi afvbw yehvv xyi gfqdse iekmfrrpzdrxzse fj xyi jzich sw tsdtlxrxzseec xifqvxic, fjkie xmmie zr xyi trwk, xyek klv nsipu rvfyeh yj zw xyvvi-hzqvrjmfrrp eeh ulijxzsew lfa xymekj zr xymj nsipu iiceki xf vetl sklvv eii melvvvrkpp xifqvximt. Xrov dsmmek e tzees xyvfyxl e hfsi-wvrqv rru gprremek e jcmxlk-gekl xyek rzfmuw gfpcmjmfrj nmkl sklvv ezvgprrvw ej kaf vbrqgpvw. Zx wyslpu klvvvjfvv esk jyitimji xyek tsdtlxzrx gvftvvkmvw esslx xyiji kvsdikvzg xymekj rru klvmi zrkiietxzse rvv tsdqfr-tceti eeh mdtfvkeex. Nlzpv klzw mj jxzpc r mecmu rvxydiex, ni afych pzov ks edieh xyek dsjx sw klv xifqvximt hyvwkmfrj giftci gfrtiir xyidwvpmij nmkl lrzv ks hf nmkl lfa xymekj rvv tservgkiu. Mk zw mdtfvkeex xyek ymxlnepw eii wljwmtmvrkpp jxiezkyx eeh wdsfxy ks wltgsix xyi himmmek sw wejx grvj, flx eesklvv ijwvrkmrp tisgiixp, aymtl av lwlecpp kebi jfv kieexvh, zw xyek ymxlnepw eii gfrkmeyfyj, mehviu tservgkmek E xf S, eeh rfx nlwk rtgvfbzqrxvpp. Xyi gfviijtfrumek wlfwmvpu fj gfqgykekmfrrp kvsdikvp zw swxvr vvjvviiu ks ej tsdtlxrxzseec ksgscsxc. R xsfh tfvkmfr sw fyi vjwsixj dep si gcejwzjziu ks fvpfrx ks xymj jysjzich eeh eii himmie sc egtcmtekmfrj zr e zrvzikc sw fxyii wmvpuw, klv gvvhzgkmfr sw klv jxiytxlvv fj jfpuiu gvfxvmew eeh xyi vvgfrjxiytxzse fj llqrr sikrrj sizrx kaf. Xyi lrpcqrvb fj slv afvb zw jrwk rpxsimkldw xyek zqgpvqvrk deklvqrxzgrp qfhvpj ks swjvv mewzkyxj zrks eeh eewniiw xf jytl ulijxzsew. Av rvv vbgpfvzrx zwjyvw wlgy rw lfa xvgyrzulij wsi jsczzrx gvffcidw grr fv umjgfzvvvh, zqgvfzvh, rrrppdvh, rru uidsewkvrxvh xf si gfviitx si ftkmdec. Av vbgitx xf debi pveumek gfrkvzflxzsew me tsdtlxrxzseec xifqvxic, xifqvximt dsuicmek, ueke wkvlgkyiij, lzky-giijfvdeegv tsdtlxzrx, M/S-iwjzgziegp wsi vbkiirrp qvqfvp, kvsxvrtymt zrwsiqrxzse jcjxvqj (KZW), fzscsxmtec tsdtlxzrx, eeh hrxr tsdtiijwzse. Sitelwv fj xyi kvsdikvzg rrxlvv fj xyi tycjmtec nsipu zr aymtl av cmmi, xifqvximt gvffcidw eimji me rrp rvve xyek zrkiietxj nmkl xyi tycjmtec nsipu. Kvsdikvzg gfqgykmek jfglwvw se uijmxrzrx, eeeccqmek, rru zqgpvqvrkmek iwjzgziex eckfvzxyqj wsi xifqvximt gvffcidw. Klv xifqvximt tsdtlxzrx xvfyg fj xyi hvtrvkqvrk zw mexvveekmfrrpcc vvrfaeiu wsi zxj tseximsykmfrj ks xyi jlruediexrp tisspvqj zr gfqgykekmfrrp kvsdikvp, euhiijwzrx dejwzzv ueke qrrrkvqvrk zwjyvw me xifqvximt gvffcidw, rru rtgppmek kvsdikvzg xvgyrzulij ks e zrvzikc sw rvvej, megcyumek qfpvglprv fzscsxc, xifqvximt dsuicmek, issskmtw, xifkieglzg mejfvdekmfr wpwkidw, vgfpfkp, eeh tysksemtw. Xyi kislt etxzzvpp tscprffvrxvw azxy fxyii xvfygw azxyme klv uigeixdiex eeh azxy klv iijirvtlvvj zr sklvv hvtrvkqvrkw ek Uybi. Klvc gfpcessieki azxy wetycxp zr Qrxyidekmtw, Smfpfkp, Fzstlvqzwkvp, Icitximtec rru Tsdtlxvv Iekzrviimek, rru klv Emtlfprw Wtlfsc fj Iezzvfrdiex. Sipseh Hlov, xyi kislt ecwf tscprffvrxvw azxy iijirvtlvvj rx zrvzslw xft mewkmkykij. Fvgryji sw zxj uigxy rru svveuxy, xyi kvsdikvzg gfqgykmek kislt ek Uybi mj rvxyrfcc xyi xft kvsdikvzg gfqgykmek kislt me klv eekmfr.";
    System.out.println("Decrypted message = " + decryptTwoKeys(message));
}

}
