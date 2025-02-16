package ru.twinown.openofficetestapp

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.Rule
import org.junit.Test
import ru.twinown.openofficetestapp.office.documentPage.DocumentPage
import ru.twinown.openofficetestapp.office.loginPage.LoginPage
import ru.twinown.openofficetestapp.office.profilePage.ProfilePage

@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(LoginActivity::class.java)
    private lateinit var loginPage: LoginPage
    private lateinit var documentPage: DocumentPage
    private lateinit var profilePage: ProfilePage
    private lateinit var navigationUi:NavigationUi

    @Before
    fun setUp() {

        loginPage = LoginPage()
        documentPage = DocumentPage()
        profilePage = ProfilePage()
        navigationUi = NavigationUi()
    }

    /*
    * UGTC-01
    */
    @Test
    fun caseNumber1() {

        loginPage.assertInitialState()

        loginPage.addPortalText(portal = "https://testdocspaceportal.onlyoffice.com/")
        loginPage.assertValidPortalAddedState()

        loginPage.addEmailText(email = "1one.test901@gmail.com")
        loginPage.assertValidEmailAddedState()

        loginPage.addPasswordText(password = "Testpass123")
        loginPage.assertCorrectPasswordState()

        loginPage.clickLoginAndWait()
        documentPage.assertDocumentsState()

        documentPage.clickNewFolderAndWait()
        documentPage.assertDocumentNewFolderState()

        navigationUi.clickProfile()
        profilePage.assertProfileState()

        profilePage.clickLogout()
        loginPage.assertInitialState()

    }

    @Test
    fun caseNumber2() {

        loginPage.assertInitialState()


        loginPage.addPortalText("testdocspaceportal.onlyoffice.com/")
        loginPage.assertInvalidPortalState()

        loginPage.addPortalText(portal = "https://testdocspaceportal.onlyoffice.com/")
        loginPage.assertValidPortalAddedState()

        loginPage.addEmailText("1one.test901")
        loginPage.assertInvalidEmailAddedState()


        loginPage.addEmailText(email = "1one.test901@gmail.com")
        loginPage.assertValidEmailAddedState()

        //todo пока не пройдёт
        loginPage.addPasswordText("stpa")
        loginPage.clickLoginAndWait()// ожидаем ошибку!
        loginPage.assertWrongPasswordOrEmailState()

        loginPage.addPasswordText("1one.test901")
        loginPage.assertCorrectPasswordState()
        disableWifi() // проверка отсутствия интернета

        loginPage.clickLoginAndWait()
        loginPage.assertNoInternetState()
        enableWifi()

        loginPage.clickLoginAndWait()
        loginPage.assertDocumentsState()

        loginPage.clickNewFolderAndWait()
        loginPage.assertDocumentNewFolderState()

        loginPage.clickProfileAndWait()
        loginPage.assertProfileState()

        loginPage.clickLogout()
        loginPage.assertInitialState()
        loginPage.checkLoginButtonDisabled()

    }

    private fun enableWifi() {
        Runtime.getRuntime().exec("adb shell svc wifi enable")
    }

    private fun disableWifi() {
        Runtime.getRuntime().exec("adb shell svc wifi disable")
    }
}