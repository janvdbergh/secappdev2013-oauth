package be.aca.oauth2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import be.aca.oauth2.constants.OAuth2Provider;
import be.aca.oauth2.util.CredentialsDialog;
import be.aca.oauth2.util.Util;

/**
 * Client Credentials Flow implementation.
 */
public class ResourceOwnerPasswordFlow {

    public static void main(String[] args) throws Exception {
        // Get credentials
        CredentialsDialog credentialsDialog = new CredentialsDialog();
        if (!credentialsDialog.askCredentials()) {
            return;
        }

        String userName = credentialsDialog.getUserName();
        String password = credentialsDialog.getPassword();
        String securityToken = credentialsDialog.getSecurityToken();

        // Prepare parameters
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("grant_type", "password"));
        parameters.add(new BasicNameValuePair("client_id", OAuth2Provider.FACEBOOK_CLIENT_ID));
        parameters.add(new BasicNameValuePair("client_secret", OAuth2Provider.FACEBOOK_CLIENT_ID));
        parameters.add(new BasicNameValuePair("username", userName));
        parameters.add(new BasicNameValuePair("password", password + securityToken));

        // Call Salesforce
        String response = Util.sendHttpPost(OAuth2Provider.FACEBOOK_TOKEN_URL, parameters);
        Map<String, String> values = Util.parseJson(response);

        System.out.println(values);
    }


}
