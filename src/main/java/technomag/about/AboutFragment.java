package technomag.about;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import technomag.technotodolist.R;

public class AboutFragment extends Fragment {

	private String version;

	private void onBackPressed()
	{
		FragmentManager fm = getActivity().getSupportFragmentManager();
		fm.popBackStack();
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		try {
			PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			version = packageInfo.versionName;
		} catch (PackageManager.NameNotFoundException e) {

		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
													 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_about, container, false);
		TextView tvAppName = (TextView) view.findViewById(R.id.tvAppName);

		tvAppName.setText(getResources().getString(R.string.app_name) + " " + version);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ImageView imgTaskBack = (ImageView) view.findViewById(R.id.imgAboutBack);
		imgTaskBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onBackPressed();
			}
		});
	}

}
