import Keycloak from 'keycloak-js';

const keycloak = new Keycloak({
  url: 'http://localhost:8080/', 
  realm: 'management-system',
  clientId: 'frontend-client',
});

const initKeycloak = (onAuthenticatedCallback) => {
  keycloak.init({ onLoad: 'login-required' }).then(authenticated => {
    if (authenticated) {
      console.info("Authenticated");
      localStorage.setItem("bearer-token", keycloak.token);
      localStorage.setItem("refresh-token", keycloak.refreshToken);

      
      setInterval(() => {
        keycloak.updateToken(70).then(refreshed => {
          if (refreshed) {
            console.debug('Token refreshed', refreshed);
            localStorage.setItem("bearer-token", keycloak.token);
          } else {
            console.warn('Token not refreshed, valid for '
              + Math.round(keycloak.tokenParsed.exp + keycloak.timeSkew - new Date().getTime() / 1000) + ' seconds');
          }
        }).catch(() => {
          console.error('Failed to refresh token');
        });
      }, 60000);

      onAuthenticatedCallback();
    } else {
      window.location.reload();
    }
  }).catch(() => {
    console.error("Authentication Failed");
  });
};

const LogOut = () => {
  keycloak.logout({
    redirectUri: window.location.origin 
  }).then(() => {
    localStorage.removeItem("bearer-token");
    localStorage.removeItem("refresh-token");
  }).catch(err => {
    console.error('Logout failed', err);
  });
};

export { keycloak, initKeycloak, LogOut };
