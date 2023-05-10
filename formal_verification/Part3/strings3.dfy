
// Time spent : Kamil - 2hrs
//				Michael - 3hrs

predicate isPrefixPred(pre:string, str:string)
{
	(|pre| <= |str|) && 
	pre == str[..|pre|]
}

predicate isNotPrefixPred(pre:string, str:string)
{
	(|pre| > |str|) || 
	pre != str[..|pre|]
}

lemma PrefixNegationLemma(pre:string, str:string)
	ensures  isPrefixPred(pre,str) <==> !isNotPrefixPred(pre,str)
	ensures !isPrefixPred(pre,str) <==>  isNotPrefixPred(pre,str)
{}

method isPrefix(pre: string, str: string) returns (res:bool)
	requires 0 < |pre|
    requires |pre| <= |str|
	ensures !res <==> isNotPrefixPred(pre,str)
	ensures  res <==> isPrefixPred(pre,str)
{
	return pre == str[..|pre|];
}
predicate isSubstringPred(sub:string, str:string)
{
	(exists i :: 0 <= i <= |str| &&  isPrefixPred(sub, str[i..]))
}

predicate isNotSubstringPred(sub:string, str:string)
{
	(forall i :: 0 <= i <= |str| ==> isNotPrefixPred(sub,str[i..]))
}

lemma SubstringNegationLemma(sub:string, str:string)
	ensures  isSubstringPred(sub,str) <==> !isNotSubstringPred(sub,str)
	ensures !isSubstringPred(sub,str) <==>  isNotSubstringPred(sub,str)
{}

method isSubstring(sub: string, str: string) returns (res:bool)
	requires 0 < |sub|
    requires |sub| <= |str|
	ensures  res <==> isSubstringPred(sub, str)
	ensures !res <==> isNotSubstringPred(sub, str) // This postcondition follows from the above lemma.
{
	var i: nat := 0;
	var b: bool := false;
	while(i <= |str| - |sub|)
		decreases |str| - i
		invariant 0 <= i <= |str| && (forall j :: 0 <= j < i ==> isNotPrefixPred(sub, str[j..]))
	{
		b := isPrefix(sub, str[i..]);
		if(b) {
			return true;
		}
		i := i + 1;
	}
	return false;
}


predicate haveCommonKSubstringPred(k:nat, str1:string, str2:string)
{
	exists i1, j1 :: 0 <= i1 <= |str1|- k && j1 == i1 + k && isSubstringPred(str1[i1..j1],str2)
}

predicate haveNotCommonKSubstringPred(k:nat, str1:string, str2:string)
{
	forall i1, j1 :: 0 <= i1 <= |str1|- k && j1 == i1 + k ==>  isNotSubstringPred(str1[i1..j1],str2)
}

lemma commonKSubstringLemma(k:nat, str1:string, str2:string)
	ensures  haveCommonKSubstringPred(k,str1,str2) <==> !haveNotCommonKSubstringPred(k,str1,str2)
	ensures !haveCommonKSubstringPred(k,str1,str2) <==>  haveNotCommonKSubstringPred(k,str1,str2)
{}

method haveCommonKSubstring(k: nat, str1: string, str2: string) returns (found: bool)
	requires k <= |str1| && k <= |str2| && k >= 0
	ensures found  <==>  haveCommonKSubstringPred(k,str1,str2)
	ensures !found <==> haveNotCommonKSubstringPred(k,str1,str2) // This postcondition follows from the above lemma.
{
	if k == 0
    {
		// from discussion board tip
		assert isPrefixPred(str1[0..0],str2[0..]);
        return true;
    }

    var i: nat := 0;
    while(i <= |str1| - k)
        decreases |str1| - k - i
		invariant 0 <= k <= |str1|
        invariant 0 <= i <= |str1| - k + 1  && (forall j, x :: 0 <= j < i && x == j + k ==> isNotSubstringPred(str1[j..x], str2))
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

method maxCommonSubstringLength(str1: string, str2: string) returns (len:nat)
	requires (0 <= |str1|)
	requires (0 <= |str2|)
	requires (|str1| <= |str2|)
	ensures (forall k :: len < k <= |str1| ==> !haveCommonKSubstringPred(k,str1,str2))
	ensures haveCommonKSubstringPred(len,str1,str2)
	ensures len >= 0;
{
    len := |str1|;
    var commonSubstring: bool := false;
    
    while(len > 0)
		decreases |str1| - (|str1| - len)
        invariant 0 <= len <= |str1| + 1
        invariant (forall k :: len < k <= |str1| ==> !haveCommonKSubstringPred(k,str1,str2))
    {
        commonSubstring := haveCommonKSubstring(len, str1, str2);
        if(commonSubstring) {
            break;
        }
        len := len - 1;
    }
	// from discussion board tip
	assert isPrefixPred(str1[|str1|..|str1|], str2[0..]);
    return len;
}