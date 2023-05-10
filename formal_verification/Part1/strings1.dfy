
// Time spent by team members: 
//  Kamil 3 hrs
//  Michael 2 hours

// 1. The following method should return true if and only if pre is a prefix of str. That is, str starts with pre.
method isPrefix(pre: string, str: string) returns (res:bool)
    requires 0 < |pre|
    requires |pre| <= |str|
{
    var i: nat := 0;
    var n: nat := |pre|;
    while(i < n)
        decreases n - i
        invariant 0 <= i <= n
    {
        if(pre[i] != str[i])
        {
            return false;
        }
        i := i + 1;
    }
    return true;
}


// 2. The following method should return true if and only if sub is a substring of str. That is, str contains sub.
method isSubstring(sub: string, str: string) returns (res:bool)
    requires 0 < |sub|
    requires |sub| <= |str|
{
    var i: nat := 0;
    var n: nat := |str|;
    var b: bool := false;
    while(i < n)
        decreases n - i
        invariant 0 <= i <= n
    {
        var tmp := str[i..];
        if |tmp| >= |sub|
        {
            b := isPrefix(sub, tmp);
            if b == true
            {
                return true;
            }
        }
        i := i + 1;
    }
    return false;
}


// 3. The following method should return true if and only if str1 and str1 have a common substring of length k.
method haveCommonKSubstring(k: nat, str1: string, str2: string) returns (found: bool)
    requires k <= |str1| && k <= |str2| && k >= 0
{
    if k == 0
    {
        return true;
    }
      
    var i: nat := 0;
    while(i <= |str1| - k)
        decreases |str1| - k - i 
        invariant 0 <= i <= |str1| - k + 1
    {
        var tmp: string := str1[i..i+k];
        if |tmp| > 0
        {
            var isSub: bool := isSubstring(tmp, str2);
            if(isSub){
                return true;
            }
        }
        i := i + 1;
    }
    
    return false;
}


//4. The following method should return the natural number len which is equal to the length of the longest common substring of str1 and str2. Note that every two strings have a common substring of length zero. 
method maxCommonSubstringLength(str1: string, str2: string) returns (len:nat)
    ensures len >= 0;
{
    var shorter: string := str1;
    var longer: string := str2;

    if |str2| < |str1|
    {
        shorter := str2;
        longer := str1;
    }

    var k: nat := |shorter|;
    var commonSubstring: bool := false;
    
    while(k > 0)
    {
        commonSubstring := haveCommonKSubstring(k, shorter, longer);
        if(commonSubstring) {
            break;
        }
        k := k - 1;
    }
    return k;
}


method Main()
{
   var pre: string := "aab";
   var str: string := "bbbbaabb";
   var b := isPrefix(pre, str);
   print "isPrefix : ";
   print b;
   print "\n\n";

   var ss := isSubstring(pre, str);
   print "isSubstring : ";
   print ss;
   print "\n\n";

   var str1: string := "abbb";
   var str2: string := "bbba";
   var k: nat := 4;
   var sa := haveCommonKSubstring(k, str1, str2);
   print "haveCommonKSubstring : ";
   print sa;
   print "\n\n";

   var m: string := "aaaaabbb";
   var n: string := "bbbagfgaaabfgf";
   var l := maxCommonSubstringLength(m, n);
   print "maxCommonSubstringLength : ";
   print l;
   print "\n\n";

}
