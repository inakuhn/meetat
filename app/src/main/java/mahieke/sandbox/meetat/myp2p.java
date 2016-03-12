package mahieke.sandbox.meetat;

import android.content.Context;
import android.util.Log;

import ch.uepaa.p2pkit.P2PKitClient;
import ch.uepaa.p2pkit.P2PKitStatusCallback;
import ch.uepaa.p2pkit.StatusResult;
import ch.uepaa.p2pkit.StatusResultHandling;
import ch.uepaa.p2pkit.discovery.InfoTooLongException;
import ch.uepaa.p2pkit.discovery.P2PListener;
import ch.uepaa.p2pkit.discovery.entity.Peer;

/**
 * Created by mahieke on 12.03.16.
 */
public class myp2p {
    private final String APP_KEY = "eyJzaWduYXR1cmUiOiJWbDk4NkY0cjMyWVRrYUl0UWhxdkRrdjJ3V3I2ZXJDMTRpVjZFSmI2UnVFT1luZXhFclhEc1k4Zi9PSkRCVi8yZUdqMXp5Vi9CRmEwWS8xdWtwUVFCaGFWcWlZR3FFU1dlTnBnOHUrNE1xa2VWSjFlNGdES3dQbXJ4TkJxTVNUbURLdUdiZlZFQzhsc01lTHk0R1REd2djM0pDcjlSQ2FOS0JyTVBHc3JEVlk9IiwiYXBwSWQiOjE0ODMsInZhbGlkVW50aWwiOjE2OTYyLCJhcHBVVVVJRCI6IjQxMDEyMjRELURDOEEtNDMyRC05NkQyLUY1NENCMUMxMjVGQyJ9";
    private final String TAG = "MESSAGE";
    private Context context;

    public myp2p(Context context) {
        this.context = context;
        final StatusResult result = P2PKitClient.isP2PServicesAvailable(context);
        if (result.getStatusCode() == StatusResult.SUCCESS) {
            P2PKitClient client = P2PKitClient.getInstance(context);
            client.enableP2PKit(mStatusCallback, APP_KEY);
        } else {
            StatusResultHandling.showAlertDialogForStatusError(context, result);
        }

        P2PKitClient.getInstance(context).getDiscoveryServices().addP2pListener(mP2PDiscoveryListener);

        try {
            P2PKitClient.getInstance(context).getDiscoveryServices().setP2pDiscoveryInfo("Hello p2pkit".getBytes());
        } catch (InfoTooLongException e) {
            Log.d(TAG, "P2PListener | The discovery info is too long");
        }
    }

    private final P2PKitStatusCallback mStatusCallback = new P2PKitStatusCallback() {
        @Override
        public void onEnabled() {
            // ready to start discovery
        }

        @Override
        public void onSuspended() {
            // p2pkit is now suspended
        }

        @Override
        public void onError(StatusResult statusResult) {
            // enabling failed, handle statusResult
        }
    };

    private final P2PListener mP2PDiscoveryListener = new P2PListener() {
        @Override
        public void onP2PStateChanged(int state) {
            Log.d(TAG, "P2PListener | State changed: " + state);
        }

        @Override
        public void onPeerDiscovered(Peer peer) {
            Log.d(TAG, "P2PListener | Peer discovered: " + peer.getNodeId() + " with info: " + new String(peer.getDiscoveryInfo()));
        }

        @Override
        public void onPeerLost(Peer peer) {
            Log.d(TAG, "P2PListener | Peer lost: " + peer.getNodeId());
        }

        @Override
        public void onPeerUpdatedDiscoveryInfo(Peer peer) {
            Log.d(TAG, "P2PListener | Peer updated: " + peer.getNodeId() + " with new info: " + new String(peer.getDiscoveryInfo()));
        }
    };

}
