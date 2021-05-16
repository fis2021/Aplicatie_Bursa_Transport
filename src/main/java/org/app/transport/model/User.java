package org.app.transport.model;

import org.dizitart.no2.objects.Id;

public class User {
    @Id
    private String username;
    private String password;
    private String role;
    private String good;

    public User(String username, String password, String role, String good) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.good = good;
    }

    public User() {
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public String getGood() {
        return good;
    }
    public void setSomething(String a)
    {
        if(good.compareTo("*")==0)
        {
            good = good + a;
            good=good.substring(1);
        }
        else if(good.isEmpty()==true)
        {
            good=good+a;
        }
        else
        good=good+"/"+a;
    }
    public void set(String a)
    {
        good=a;
    }
    public void setRole(String role) {
            this.role = role;
        }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

       if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
       if (good != null ? !good.equals(user.good) : user.good != null) return false;
        return role != null ? role.equals(user.role) : user.role == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (good != null ? good.hashCode() : 0);
        return result;
    }

    public void setAccept(String offer){

            String b="*";
            boolean sw=true;
            String[] split = good.split("/");
            int k = 0;


            for (String s : split) {
                if(s.compareTo(offer) == 0) {
                    split[k] = split[k].replace("In pending","Accepted");

                }
                k++;
            }

        for(String s:split)
        {
            if(sw==true)
            {
                b=b+s;
                sw=false;
            }
            else
            {
                b=b+"/"+s;
            }
        }

        b=b.substring(1);
        good = b;

    }

    public void setReject(String offer){

        String b="*";
        boolean sw=true;
        String[] split = good.split("/");
        int k = 0;


        for (String s : split) {
            if(s.compareTo(offer) == 0) {
                split[k] = split[k].replace("In pending","Rejected");

            }
            k++;
        }

        for(String s:split)
        {
            if(sw==true)
            {
                b=b+s;
                sw=false;
            }
            else
            {
                b=b+"/"+s;
            }
        }

        b=b.substring(1);
        good = b;

    }

    public void setClose(String offer){

        String b="*";
        boolean sw=true;
        String[] split = good.split("/");
        int k = 0;

        for (String s : split) {
            if(s.compareTo(offer) == 0) {
                split[k] = split[k].replace("Accepted","Closed");

            }
            k++;
        }

        for(String s:split)
        {
            if(sw)
            {
                b=b+s;
                sw=false;
            }
            else
                b=b+"/"+s;
        }
        b=b.substring(1);
        good = b;
    }

}


