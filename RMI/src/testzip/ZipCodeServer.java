package testzip;

import ror.Remote440;

public interface ZipCodeServer extends Remote440// extends YourRemote or whatever
{
    public void initialise(ZipCodeList newlist);
    public String find(String city);
    public ZipCodeList findAll();
    public void printAll();
}

