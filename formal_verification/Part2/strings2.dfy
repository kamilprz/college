//Number of hours: 1

predicate isPrefixPred(pre:string, str:string)
{
	|pre| <= |str| && pre == str[..|pre|]
}

predicate isNotPrefixPred(pre:string, str:string)
{
    |pre| > |str| || pre != str[..|pre|]
}

// Sanity check: Dafny should be able to automatically prove the following lemma
lemma PrefixNegationLemma(pre:string, str:string)
	ensures  isPrefixPred(pre,str) <==> !isNotPrefixPred(pre,str)
	ensures !isPrefixPred(pre,str) <==>  isNotPrefixPred(pre,str)
{}

predicate isSubstringPred(sub:string, str:string)
{
    exists i : int :: 0 <= i <= |str| && isPrefixPred(sub, str[i..])
}

predicate isNotSubstringPred(sub:string, str:string)
{
    forall i : int :: 0 <= i <= |str| ==> isNotPrefixPred(sub, str[i..])
}

// Sanity check: Dafny should be able to automatically prove the following lemma
lemma SubstringNegationLemma(sub:string, str:string)
	ensures  isSubstringPred(sub,str) <==> !isNotSubstringPred(sub,str)
	ensures !isSubstringPred(sub,str) <==>  isNotSubstringPred(sub,str)
{}

predicate haveCommonKSubstringPred(k:nat, str1:string, str2:string)
{
    k == 0 || exists sub : string :: isSubstringPred(sub, str1) && isSubstringPred(sub, str2) && |sub| == k
}

predicate haveNotCommonKSubstringPred(k:nat, str1:string, str2:string)
{
	// k != 0 && forall sub :: !(isSubstringPred(sub, str1) && isSubstringPred(sub, str2) && |sub| == k)
	k != 0 && forall sub : string :: isNotSubstringPred(sub, str1) || isNotSubstringPred(sub, str2) || |sub| != k
}

// Sanity check: Dafny should be able to automatically prove the following lemma
lemma commonKSubstringLemma(k:nat, str1:string, str2:string)
	ensures  haveCommonKSubstringPred(k,str1,str2) <==> !haveNotCommonKSubstringPred(k,str1,str2)
	ensures !haveCommonKSubstringPred(k,str1,str2) <==> haveNotCommonKSubstringPred(k,str1,str2)
{}