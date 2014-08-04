package com.cubie.openapi.sdk.internal.intent;

import android.content.Context;
import android.content.Intent;
import android.content.pm.Signature;
import android.net.Uri;

import com.cubie.openapi.sdk.internal.util.Contexts;

public class CubieIntents {
  private static boolean checkCubieIntent(final Context context, final Intent intent) {
    final String packageName = Contexts.resolvePackageNameForIntent(context, intent);
    if (packageName == null) {
      return false;
    }

    final Signature[] signatures = Contexts.getSignatures(context, packageName);
    for (final Signature signature : signatures) {
      if (CUBIE_SIGNATURE_DEBUG.equals(signature.toCharsString())) {
        return true;
      }
      if (CUBIE_SIGNATURE_RELEASE.equals(signature.toCharsString())) {
        return true;
      }
    }
    return false;
  }

  public static Intent createConnectCubieIntent(final Context context,
      final ConnectParameters connectParameters) {
    final Intent intent = new Intent(SdkConstants.CONNECT_INTENT_ACTION);
    intent.putExtras(connectParameters.toBundle());

    if (checkCubieIntent(context, intent)) {
      return intent;
    }
    return null;
  }

  public static Intent createDisconnectCubieIntent(final Context context,
      final DisconnectParameters disconnectParameters) {
    final Intent intent = new Intent(SdkConstants.DISCONNECT_INTENT_ACTION);
    intent.putExtras(disconnectParameters.toBundle());

    if (checkCubieIntent(context, intent)) {
      return intent;
    }
    return null;
  }

  public static Intent createGooglePlayIntentForCubie(final Context context) {
    final Intent intent = new Intent(Intent.ACTION_VIEW,
        Uri.parse(String.format(SdkConstants.CUBIE_GOOGLE_PLAY_URL_PATTERN,
            context.getPackageName())));
    return intent;
  }

  public static Intent createReturningIntent(final Context context, final String appKey) {
    final Intent intent = new Intent(Intent.ACTION_VIEW,
        Uri.parse(String.format(SdkConstants.CUBIE_RETURN_URL_PATTERN, appKey)));

    final String packageName = Contexts.resolvePackageNameForIntent(context, intent);
    if (packageName == null) {
      return null;
    }

    if (!context.getPackageName().equals(packageName)) {
      return null;
    }

    return intent;
  }

  private static final String CUBIE_SIGNATURE_DEBUG = "308201e53082014ea00302010202044e37c336300d06092a864886f70d01010505003037310b30090603550406130255533110300e060355040a1307416e64726f6964311630140603550403130d416e64726f6964204465627567301e170d3131303830323039323832325a170d3431303732353039323832325a3037310b30090603550406130255533110300e060355040a1307416e64726f6964311630140603550403130d416e64726f696420446562756730819f300d06092a864886f70d010101050003818d003081890281810095b097b926f67d85f122ed1b077840d73219dfdbcbbea55e29929acec3aed9316603d14c85322bab0491700052dbafaa92d29bcbf88ab0516643fe379d92849d8408aa8a6862af8b933d58ffe32b3173e374ccedf5728c0eaddcca54b7a871c9eec709dc13349c682dbfd10797fb28fd5babac44d7b73ab8c19043dd4a8c0c370203010001300d06092a864886f70d01010505000381810052d63dc8f8d20f2c32a69fd51d850c3301e42d0e5378acfe5bbeccf3c4ff87f97c9dd02ee1415492924ea385b618dcc5d0008abc4402711ee62d2757807e39cfcd266f4b17571edcc8c493c1a5d41574198c1ca87cf610595fabb5c33bf68ed61d09bcfac1f0bf7458553fc2dc5d11e56a75c0e8beefdc57e44e48e2de211524";

  private static final String CUBIE_SIGNATURE_RELEASE = "308202233082018ca00302010202044f38d629300d06092a864886f70d01010505003055310b3009060355040613027477310f300d0603550408130654616977616e310f300d060355040713065461697065693111300f060355040a13084c69717561626c653111300f060355040b13084c69717561626c653020170d3132303231333039323134355a180f32313132303132303039323134355a3055310b3009060355040613027477310f300d0603550408130654616977616e310f300d060355040713065461697065693111300f060355040a13084c69717561626c653111300f060355040b13084c69717561626c6530819f300d06092a864886f70d010101050003818d0030818902818100b575155f1ea71d0846d3f5ce5cc6fa5806ba7ecdf55acc3b97ccb665168931d9ba14fa16402e2c4b2e61b2abf408597941854459ee05cec8f56c945d906e5121103c2e9ee8cdb8cb80fedbec869fe6ab617f0ed4b8afba04475a07873d8810d2949ea5c8ef83a44372e7f0b6026065804fbe9490bd636b35ad833aeee6271dfb0203010001300d06092a864886f70d0101050500038181000a44d67a3a738738f0223c7c739c150b791e139da46e078860a0da1b61a490abf994b06df6a358d14fe21ec56fd07036b444a5075480388ae9cdb91d7318cc0d8e0b552c2ebc4a4c82c45f8b4fc9297c59af579544196aafd96677e9a2ce2c5b4081d6153f42832ae4174621d818c9fe7647a7d8dcda24e5ba8a9b2bc7f285e0";
}
