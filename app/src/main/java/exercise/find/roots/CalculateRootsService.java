package exercise.find.roots;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class CalculateRootsService extends IntentService {


    public CalculateRootsService() {
        super("CalculateRootsService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent == null) return;
        Intent broadcast = new Intent();
        long timeStartMs = System.currentTimeMillis();
        long numberToCalculateRootsFor = intent.getLongExtra("number_for_service", 0);
        broadcast.putExtra("original_number", numberToCalculateRootsFor);

        if (numberToCalculateRootsFor <= 0) {
            Log.e("CalculateRootsService", "can't calculate roots for non-positive input" + numberToCalculateRootsFor);
            return;
        }

        for (long i = 2L; i <= (long) Math.sqrt(numberToCalculateRootsFor); ++i) {
            long timePassed = System.currentTimeMillis() - timeStartMs;
            if (timePassed >= 20000L) {
                broadcast.setAction("stopped_calculations");
                broadcast.putExtra("time_until_give_up_seconds", timePassed / 1000);
                this.sendBroadcast(broadcast);
                return;
            }
            if (numberToCalculateRootsFor % i == 0) {
                long j = numberToCalculateRootsFor / i;
                broadcast.setAction("found_roots");
                broadcast.putExtra("root1", i);
                broadcast.putExtra("root2", j);
                broadcast.putExtra("calculations_time", timePassed / 1000);
                this.sendBroadcast(broadcast);
                return;
            }
        }
        broadcast.setAction("found_roots");
        broadcast.putExtra("root1", numberToCalculateRootsFor);
        broadcast.putExtra("root2", 1L);
        broadcast.putExtra("calculations_time", (System.currentTimeMillis() - timeStartMs) / 1000);
        this.sendBroadcast(broadcast);
    }
}