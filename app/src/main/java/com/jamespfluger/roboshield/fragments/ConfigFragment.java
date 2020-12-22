package com.jamespfluger.roboshield.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.jamespfluger.roboshield.R;
import com.jamespfluger.roboshield.utils.Constants;
import com.jamespfluger.roboshield.utils.PrefsUtil;

public class ConfigFragment extends Fragment {
    public ConfigFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_config, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setSwitchInitialValues();
        setSwitchContainerOnClickListeners();
        setListOnClickListeners();
    }

    private void setSwitchInitialValues() {
        SwitchCompat blockAllSwitch = getActivity().findViewById(R.id.configBlockAllNumbersSwtich);
        SwitchCompat blockPrivateCallsSwitch = getActivity().findViewById(R.id.configBlockPrivateNumbersSwtich);
        SwitchCompat blockKnownSpamSwitch = getActivity().findViewById(R.id.configBlockKnownSpamSwitch);
        SwitchCompat blockNonContactsSwitch = getActivity().findViewById(R.id.configBlockNonContactsSwitch);
        SwitchCompat useNotificationsSwitch = getActivity().findViewById(R.id.configUseNotificationsSwitch);
        SwitchCompat sendToVoicemailSwitch = getActivity().findViewById(R.id.configSendCallsToVoicemailSwitch);

        blockAllSwitch.setChecked(PrefsUtil.getBoolean(blockAllSwitch.getTag().toString()));
        blockPrivateCallsSwitch.setChecked(PrefsUtil.getBoolean(blockPrivateCallsSwitch.getTag().toString()));
        blockKnownSpamSwitch.setChecked(PrefsUtil.getBoolean(blockKnownSpamSwitch.getTag().toString()));
        blockNonContactsSwitch.setChecked(PrefsUtil.getBoolean(blockNonContactsSwitch.getTag().toString()));
        sendToVoicemailSwitch.setChecked(PrefsUtil.getBoolean(sendToVoicemailSwitch.getTag().toString()));
        useNotificationsSwitch.setChecked(PrefsUtil.getBoolean(useNotificationsSwitch.getTag().toString()));
    }

    private void setSwitchContainerOnClickListeners() {
        View blockAllContainer = getActivity().findViewById(R.id.configBlockAllNumbersContainer);
        View blockPrivateCallsContainer = getActivity().findViewById(R.id.configBlockPrivateNumbersContainer);
        View blockKnownSpamContainer = getActivity().findViewById(R.id.configBlockKnownSpamContainer);
        View blockNonContactsContainer = getActivity().findViewById(R.id.configBlockNonContactsContainer);
        View sendToVoicemailContainer = getActivity().findViewById(R.id.configSendCallsToVoicemailContainer);
        View useNotificationsContainer = getActivity().findViewById(R.id.configUseNotificationsContainer);

        blockAllContainer.setOnClickListener(createSwitchContainerOnClickListener());
        blockPrivateCallsContainer.setOnClickListener(createSwitchContainerOnClickListener());
        blockKnownSpamContainer.setOnClickListener(createSwitchContainerOnClickListener());
        blockNonContactsContainer.setOnClickListener(createSwitchContainerOnClickListener());
        sendToVoicemailContainer.setOnClickListener(createSwitchContainerOnClickListener());
        useNotificationsContainer.setOnClickListener(createSwitchContainerOnClickListener());
    }

    private void setListOnClickListeners() {
        View areaCodeListLayout = getActivity().findViewById(R.id.configBlockAreaCodesLayout);
        View allowListLayout = getActivity().findViewById(R.id.configAllowListLayout);
        View blockListLayout = getActivity().findViewById(R.id.configBlockListLayout);

        areaCodeListLayout.setOnClickListener(createOnListClickListener(Constants.AREA_CODE_LIST_KEY, R.id.action_ConfigFragment_to_AreaCodeListFragment));
        allowListLayout.setOnClickListener(createOnListClickListener(Constants.ALLOW_LIST_KEY, R.id.action_ConfigFragment_to_AllowListFragment));
        blockListLayout.setOnClickListener(createOnListClickListener(Constants.BLOCK_LIST_KEY, R.id.action_ConfigFragment_to_AllowListFragment));
    }

    private View.OnClickListener createSwitchContainerOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layout = (LinearLayout) v;
                SwitchCompat configSwitch = null;

                for (int i = 0; i < layout.getChildCount(); i++) {
                    View child = layout.getChildAt(i);

                    if (child instanceof SwitchCompat) {
                        configSwitch = (SwitchCompat) child;
                        break;
                    }
                }

                if (configSwitch != null) {
                    configSwitch.setChecked(!configSwitch.isChecked());

                    PrefsUtil.putBoolean(configSwitch.getTag().toString(), configSwitch.isChecked());
                }
            }
        };
    }

    private View.OnClickListener createOnListClickListener(final String fragmentType, final int navigationId) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.FRAGMENT_TYPE_KEY, fragmentType);

                NavHostFragment.findNavController(ConfigFragment.this).navigate(navigationId, bundle);
            }
        };
    }
}
