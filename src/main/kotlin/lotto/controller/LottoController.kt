package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoCount
import lotto.model.LottoNumber
import lotto.model.LottoNumberListGenerator
import lotto.model.Lottos
import lotto.model.Price
import lotto.model.WinNumber
import lotto.view.InputView
import lotto.view.OutputView

object LottoController {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun runLottoGame() {
        val price = inputPrice()
        val purchasedLottoList = generateLottos(price)
        printResult(price, purchasedLottoList)
    }

    private fun inputPrice(): Price {
        val price = Price(inputView.takePurchasedPrice())
        val manualLottoCount = inputView.inputManualLottoCount() ?: 0
        val manualLottoList = inputView.inputManualLottoNumber(manualLottoCount)
        outputView.resultLottoCount(LottoCount(price.lottoCount).createLottoNumber(manualLottoCount), LottoCount(price.lottoCount - manualLottoCount).createLottoNumber())
        return price
    }

    private fun generateLottos(price: Price): List<Lotto> {
        val purchasedLotto = LottoNumberListGenerator(price).generateLottoList(LottoNumber.getLottoNumberRange().shuffled())
        outputView.printWinNumbers(purchasedLotto)
        return purchasedLotto
    }

    private fun printResult(price: Price, purchasedLotto: List<Lotto>) {
        val winNumberList = inputView.inputLastLottoWinNumber()
        val bonusNumber = inputView.inputBonusNumber()
        val winNumber = WinNumber.inputWinNumber(winNumberList, bonusNumber)
        val lottos = Lottos.executeLottoComparison(purchasedLotto)
        val result = lottos.compareLottoResult(price, winNumber)
        outputView.printWinStatistic(result)
    }
}
