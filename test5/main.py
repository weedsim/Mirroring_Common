# python example for the exchange of auth code to refresh token
# Reference: https://oauth2client.readthedocs.io/en/latest/source/oauth2client.client.html#

from oauth2client import client

CLIENT_ID = '78640959211-4a1a5nt6iqoul9f9jk7idq3t5ve5ltqe.apps.googleusercontent.com'

CLIENT_SECRET = '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'


if __name__ == "__main__":
    auth_code = "4/0AWtgzh6ePHsGCMSbIMBNgmn5YP0lKOt4hqalDRsbSvvt9rXcZu5MtTuh9nd1SzvRhQ8rew"
    
    # print(dir(client))
    try:
        credentials = client.credentials_from_code(CLIENT_ID, CLIENT_SECRET, scope='', code=auth_code)
    except client.FlowExchangeError as e:
        print(e)
    else:
        print('refresh_token:', credentials.refresh_token)
        print('access_token:', credentials.access_token)
        print('token_expiry:', credentials.token_expiry)
