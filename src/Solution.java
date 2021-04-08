import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    //This algorithm is defined in a way such that it always gives the least number of changes to be done
    //A lot of things are checked because it should give the best result for every small possible case

    //When there are less than 6 characters in the password to get the minimum possible changes we need:
    //First, ADD lowercase/uppercase/digit (if we don't have one of them) or ADD anything where there are 3 repeating characters in a row because this way we solve the repeating characters,add one non existing thing(lowercase/uppercase/digit) and we are closer to 6
    //Second, check if we have lowercase/uppercase/digit and if we don't we add it to the end(This step is necessary because in the first step we add them just if we have 3 repeating characters in a row)
    //Third, check if we don't have enough letters(because we could have for example:"aB0" and it would pass the first two checks)


    //When there are more than 6 and less than 20 characters in the password to get the minimum possible changes we need:
    //First, REPLACE lowercase/uppercase/digit (if we don't have one of them) or REPLACE(we don't add them because we could pass the limit of 20) anything where there are 3 repeating characters in a row because this way we solve the repeating characters and add one non existing thing(lowercase/uppercase/digit)
    //Second, check if we have lowercase/uppercase/digit and if we don't we search where should we put them replacing the type of character with the most occurences(lowercase/uppercase/digit)(This step is necessary because in the first step we replace them just if we have 3 repeating characters in a row)



    //When there are more than 20 characters in the password to get the minimum possible changes we need:
    //First, DELETE characters where there are 3 repeating characters in a row until we reach 20(if we keep deleting is not optimal)
    //Second, is checking if we still have more than 20 characters without any 3 repeating characters in a row and DELETES from left to right(because this way we don't mess up things)(a special case here is AAA000abcdefghijklomnop because he would know to delete from left but leaving A and 0 there when just one piece of them remains there)
    //Third, REPLACE lowercase/uppercase/digit (if we don't have one of them) where there are 3 repeating characters in a row because this way we solve the repeating characters and add one non existing thing(lowercase/uppercase/digit)
    //Forth, check if we have lowercase/uppercase/digit and if we don't we search where should we put them replacing the type of character with the most occurences(lowercase/uppercase/digit)(This step is necessary because in the second step we replace them just if we have 3 repeating characters in a row)


    //l for lower
    //u for upper
    //d for digit
    public static void main(String[] args)
    {
        List<String> list=new ArrayList<>(Arrays.asList("ab000","a","aa","aaa","aaaa","aaaaa","abcdef","1","11","111","1111","11111","12345","A","AA","AAA","AAAA","AAAAA","ABCDE","aaaA0","aaaA0","aaaA0","0aaa0"
                ,"aaaaaaaaaa","abcdefghijklmnop","abcdefghA1ccc","AS01234567ccc","A0123456","SSSSSSSSSSSS","AAA000aaa","000ZZ123","ASDASDDSa0A","0abcd000"
                ,"aaaaaaaaaaaaaaaaaaaaaaaaaa","abcdefghijklmnopqrstuvwxyz","aaabcdefghijklmnopqrstuvqzaA0","BB0caazabcdfeghijklmnop0"

                , "a","aA","a0","aaa","aaa0","aaaA","abcdefg","abcd0efg","abcdAef","abcdAef1g","aaabAc1d","abcdefghijklompnhfdt","abcwwdefghij0klompnhfdt","abcdewweefghijAklompnhfdt"
                ,"aaabcdefghiwewejklompnhfdt","abcdefghiwewejklompnhbbbta","abcdefghi000ompnwewhfdt","abcdefghA0ijkleweaaampnhfdt","AAA000asdsadadewasdasdasdas"
                ,"baaacdddaabaabbccaaaabbabbb",""

        ));
        list.forEach(x->System.out.println(formatString(x)));
    }



    public static String addNonExistingChars(String s,int l,int u,int d)
    {
        if(l==0){
            if(u>1) {
                int t = 0;
                while (s.charAt(t) < 'A' || s.charAt(t)>'Z')
                    t++;
                s =s.substring(0,t)+ "a" + s.substring(t+1, s.length());
                Variables.count++;
            }
            else
            if(d>1) {
                int t = 0;
                while (s.charAt(t) < '0' || s.charAt(t)>'9')
                    t++;
                s =s.substring(0,t)+ "a" + s.substring(t+1, s.length());
                Variables.count++;
            }
        }
        if(u==0){
            if(l>1) {
                int t = 0;
                while (s.charAt(t) < 'a' || s.charAt(t)>'z')
                    t++;
                s =s.substring(0,t)+ "A" + s.substring(t+1, s.length());
                Variables.count++;
            }
            else
            if(d>1) {
                int t = 0;
                while (s.charAt(t) < '0' || s.charAt(t)>'9')
                    t++;
                s =s.substring(0,t)+ "A" + s.substring(t+1, s.length());
                Variables.count++;
            }
        }
        if(d==0) {
            if(l>1) {
                int t = 0;
                while (s.charAt(t) < 'a' || s.charAt(t)>'z')
                    t++;
                s =s.substring(0,t)+ "0" + s.substring(t+1, s.length());
                Variables.count++;
            }
            else
            if(u>1) {
                int t = 0;
                while (s.charAt(t) < 'A' || s.charAt(t)>'Z')
                    t++;
                s =s.substring(0,t)+ "0" + s.substring(t+1, s.length());
                Variables.count++;
            }
        }
        return s;
    }

    public static String formatString(String s)
    {
        System.out.println("String before:");
        System.out.println(s);
        Variables.count=0;
        int l=0,u=0,d=0,k=0;
        for (int i = 0; i < s.length(); i++)
        {
            if(s.charAt(i)>='a' && s.charAt(i)<='z')
                l++;
            if(s.charAt(i)>='A' && s.charAt(i)<='Z')
                u++;
            if(s.charAt(i)>='0' && s.charAt(i)<='9')
                d++;

        }

        if(s.length()<6)
        {
            //first
            for (int i = 1; i < s.length(); i++)
            {
                if(s.charAt(i)==s.charAt(i-1))
                {
                    k++;
                    if(k==2)
                    {
                        if(l==0)
                        {
                            Variables.count++;
                            s=s.substring(0,i)+"a"+s.substring(i,s.length());
                            l=1;
                            k=0;
                        }
                        else
                        if(u==0)
                        {
                            Variables.count++;
                            s=s.substring(0,i)+"A"+s.substring(i,s.length());
                            u=1;
                            k=0;
                        }
                        else
                        if(d==0)
                        {
                            Variables.count++;
                            s=s.substring(0,i)+"0"+s.substring(i,s.length());
                            d=1;
                            k=0;
                        }
                        else
                        {
                            char c1=s.charAt(i-1);
                            char c2;
                            if(i==s.length()-1)
                                c2='#';
                            else
                                c2=s.charAt(i+1);
                            if(!Character.isLowerCase(c1) && !Character.isLowerCase(c2))
                            {
                                s=s.substring(0,i)+"a"+s.substring(i,s.length());
                                Variables.count++;
                            }
                            else
                            if(!Character.isUpperCase(c1) && !Character.isUpperCase(c2))
                            {
                                s=s.substring(0,i)+"A"+s.substring(i,s.length());
                                Variables.count++;
                            }
                            else
                            if(!Character.isDigit(c1) && !Character.isDigit(c2))
                            {
                                s=s.substring(0,i)+"0"+s.substring(i,s.length());
                                Variables.count++;
                            }
                        }
                    }
                }
                else
                    k=0;
            }

            //second
            if(l==0)
            {
                s=s.substring(0,s.length())+"a";
                Variables.count++;
            }
            if(u==0)
            {
                s=s.substring(0,s.length())+"A";
                Variables.count++;
            }
            if(d==0)
            {
                s=s.substring(0,s.length())+"0";
                Variables.count++;
            }


            //third
            while(s.length()<6) {
                char c = s.charAt(s.length() - 1);
                if (Character.isLowerCase(c))
                {
                    s=s+"A";
                    Variables.count++;
                }
                if (Character.isUpperCase(c))
                {
                    s=s+"a";
                    Variables.count++;
                }
                if (Character.isDigit(c))
                {
                    s=s+"a";
                    Variables.count++;
                }
            }

        }
        else
        if(s.length()>=6 && s.length()<=20)
        {
            //first
            for (int i = 1; i < s.length(); i++)
            {
                if(s.charAt(i)==s.charAt(i-1))
                {
                    k++;
                    if(k==2)
                    {
                        if(l==0)
                        {
                            s=s.substring(0,i)+"a"+s.substring(i+1,s.length());
                            l=1;
                            Variables.count++;
                        }
                        else
                        if(u==0)
                        {
                            s=s.substring(0,i)+"A"+s.substring(i+1,s.length());
                            u=1;
                            Variables.count++;
                        }
                        else
                        if(d==0)
                        {
                            s=s.substring(0,i)+"0"+s.substring(i+1,s.length());
                            d=1;
                            Variables.count++;
                        }
                        else
                        {
                            char c1=s.charAt(i-1);
                            char c2;
                            if(i==s.length()-1)
                                c2='#';
                            else
                                c2=s.charAt(i+1);
                            if(!Character.isLowerCase(c1) && !Character.isLowerCase(c2))
                            {
                                s=s.substring(0,i)+"a"+s.substring(i+1,s.length());
                                Variables.count++;
                            }
                            else
                            if(!Character.isUpperCase(c1) && !Character.isUpperCase(c2))
                            {
                                s=s.substring(0,i)+"A"+s.substring(i+1,s.length());
                                Variables.count++;
                            }
                            else
                            if(!Character.isDigit(c1) && !Character.isDigit(c2))
                            {
                                s=s.substring(0,i)+"0"+s.substring(i+1,s.length());
                                Variables.count++;
                            }
                        }

                    }
                }
                else
                    k=0;
            }

            //second
            if(l==0){
                if(u>1) {
                    int t = 0;
                    while (s.charAt(t) < 'A' || s.charAt(t)>'Z')
                        t++;
                    s =s.substring(0,t)+ "a" + s.substring(t+1, s.length());
                    Variables.count++;
                }
                else
                if(d>1) {
                    int t = 0;
                    while (s.charAt(t) < '0' || s.charAt(t)>'9')
                        t++;
                    s =s.substring(0,t)+ "a" + s.substring(t+1, s.length());
                    Variables.count++;
                }
            }
            if(u==0){
                if(l>1) {
                    int t = 0;
                    while (s.charAt(t) < 'a' || s.charAt(t)>'z')
                        t++;
                    s =s.substring(0,t)+ "A" + s.substring(t+1, s.length());
                    Variables.count++;
                }
                else
                if(d>1) {
                    int t = 0;
                    while (s.charAt(t) < '0' || s.charAt(t)>'9')
                        t++;
                    s =s.substring(0,t)+ "A" + s.substring(t+1, s.length());
                    Variables.count++;
                }
            }
            if(d==0) {
                if(l>1) {
                    int t = 0;
                    while (s.charAt(t) < 'a' || s.charAt(t)>'z')
                        t++;
                    s =s.substring(0,t)+ "0" + s.substring(t+1, s.length());
                    Variables.count++;
                }
                else
                if(u>1) {
                    int t = 0;
                    while (s.charAt(t) < 'A' || s.charAt(t)>'Z')
                        t++;
                    s =s.substring(0,t)+ "0" + s.substring(t+1, s.length());
                    Variables.count++;
                }
            }
        }
        else
        if(s.length()>20)
        {
            int i;
            //first
            for (i = 1; i < s.length(); i++) {
                if(s.charAt(i)==s.charAt(i-1))
                {
                    k++;
                    if(k==2 && s.length()>20)
                    {
                        if(Character.isLowerCase(s.charAt(i)))
                            l--;
                        else
                        if(Character.isUpperCase(s.charAt(i)))
                            u--;
                        else
                        if(Character.isDigit(s.charAt(i)))
                            d--;
                        s=s.substring(0,i)+s.substring(i+1,s.length());
                        k=0;
                        i--;
                        Variables.count++;
                    }
                }
                else
                    k=0;
            }
            //second
            int ok=0;
            i=1;
            k=0;
            while(i<s.length() && ok==0)
            {
                if(s.charAt(i)==s.charAt(i-1))
                {
                    k++;
                    if(k==2)
                    {
                        ok=1;
                    }
                }
                else
                    k=0;
                i++;
            }
            if(ok==0)
            {
                i=0;
                while(i<s.length() && s.length()>20)
                {
                    if(Character.isLowerCase(s.charAt(i)) && l>1)
                    {
                        s=s.substring(0,i)+s.substring(i+1,s.length());
                        i--;
                        l--;
                        Variables.count++;
                    }
                    else
                    if(Character.isUpperCase(s.charAt(i)) && u>1)
                    {
                        s=s.substring(0,i)+s.substring(i+1,s.length());
                        i--;
                        u--;
                        Variables.count++;
                    }
                    else
                    if(Character.isDigit(s.charAt(i)) && d>1)
                    {
                        s=s.substring(0,i)+s.substring(i+1,s.length());
                        i--;
                        d--;
                        Variables.count++;
                    }
                    i++;
                }
            }
            //third
            k=0;
            for (i = 1; i < s.length(); i++) {
                if(s.charAt(i)==s.charAt(i-1))
                {
                    k++;
                    if(k==2)
                    {
                        if(l==0)
                        {
                            s=s.substring(0,i)+"a"+s.substring(i+1,s.length());
                            l=1;
                            Variables.count++;
                        }
                        else
                        if(u==0)
                        {
                            s=s.substring(0,i)+"A"+s.substring(i+1,s.length());
                            u=1;
                            Variables.count++;
                        }
                        else
                        if(d==0)
                        {
                            s=s.substring(0,i)+"0"+s.substring(i+1,s.length());
                            d=1;
                            Variables.count++;
                        }
                        else
                        {
                            char c1=s.charAt(i-1);
                            char c2;
                            if(i==s.length()-1)
                                c2='#';
                            else
                                c2=s.charAt(i+1);
                            if(!Character.isLowerCase(c1) && !Character.isLowerCase(c2))
                            {
                                s=s.substring(0,i)+"a"+s.substring(i+1,s.length());
                                Variables.count++;
                            }
                            else
                            if(!Character.isUpperCase(c1) && !Character.isUpperCase(c2))
                            {
                                s=s.substring(0,i)+"A"+s.substring(i+1,s.length());
                                Variables.count++;
                            }
                            else
                            if(!Character.isDigit(c1) && !Character.isDigit(c2))
                            {
                                s=s.substring(0,i)+"0"+s.substring(i+1,s.length());
                                Variables.count++;
                            }
                        }

                    }
                }
                else
                    k=0;
            }

            //Forth
            s=addNonExistingChars(s,l,u,d);
        }
        return "String after:\n" + s+" "+"\nChanges done:"+Variables.count+"\n";
    }
}
