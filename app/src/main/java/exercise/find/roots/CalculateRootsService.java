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


        for (long i = 2; i <= (long) Math.sqrt(numberToCalculateRootsFor); ++i) {
            long timePassed = System.currentTimeMillis() - timeStartMs;
            if (timePassed >= 20) {
                broadcast.setAction("stopped_calculations");
                broadcast.putExtra("original_number", numberToCalculateRootsFor);
                broadcast.putExtra("time_until_give_up_seconds", timePassed);
                this.sendBroadcast(broadcast);
                return;
            }
            if (numberToCalculateRootsFor % i == 0) {
                long j = numberToCalculateRootsFor / i;
                broadcast.setAction("found_roots");
                broadcast.putExtra("root1", i);
                broadcast.putExtra("root2", j);
                this.sendBroadcast(broadcast);
                return;
            }
        }
        broadcast.setAction("found_roots");
        broadcast.putExtra("root1", numberToCalculateRootsFor);
        broadcast.putExtra("root2", 1);
        this.sendBroadcast(broadcast);
    /*
    TODO:
     calculate the roots.
     check the time (using `System.currentTimeMillis()`) and stop calculations if can't find an answer after 20 seconds
     upon success (found a root, or found that the input number is prime):
      send broadcast with action "found_roots" and with extras:
       - "original_number"(long)
       - "root1"(long)
       - "root2"(long)
     upon failure (giving up after 20 seconds without an answer):
      send broadcast with action "stopped_calculations" and with extras:
       - "original_number"(long)
       - "time_until_give_up_seconds"(long) the time we tried calculating

      examples:
       for input "33", roots are (3, 11)
       for input "30", roots can be (3, 10) or (2, 15) or other options
       for input "17", roots are (17, 1)
       for input "829851628752296034247307144300617649465159", after 20 seconds give up

     */
    }
}