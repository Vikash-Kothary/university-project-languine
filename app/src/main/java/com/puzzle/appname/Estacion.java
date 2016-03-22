package com.puzzle.appname;

/**
 * Created by fauco on 22/03/2016.
 */
public class Estacion
{
    private String name;
    private String month1;
    private String month2;
    private String month3;

    public Estacion(String name, String month1, String month2, String month3)
    {
        this.name = name;
        this.month1 = month1;
        this.month2 = month2;
        this.month3 = month3;
    }

    public String getName() { return name; }

    public String getMonth1() { return month1; }

    public String getMonth2() { return month2; }

    public String getMonth3() { return month3; }
}
