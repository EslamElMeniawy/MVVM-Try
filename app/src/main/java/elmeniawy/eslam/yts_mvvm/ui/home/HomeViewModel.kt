package elmeniawy.eslam.yts_mvvm.ui.home

import androidx.lifecycle.ViewModel
import elmeniawy.eslam.yts_mvvm.model.api.ApiRepo
import elmeniawy.eslam.yts_mvvm.model.database.DatabaseRepo
import javax.inject.Inject

/**
 * HomeViewModel
 *
 * Created by Eslam El-Meniawy on 18-Apr-2019.
 * Roqay
 */
class HomeViewModel @Inject constructor(apiRepository: ApiRepo, databaseRepository: DatabaseRepo) :
    ViewModel() {
}