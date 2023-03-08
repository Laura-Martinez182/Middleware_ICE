module Demo
{
    interface Printer
    {
        void printString(string s);
        void verifyGUID(string guid, int n);
        int searchNextPrime(int n);
        bool isPrime(int n);
    }
}