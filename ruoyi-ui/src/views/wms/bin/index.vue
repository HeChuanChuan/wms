<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      v-show="showSearch"
      :inline="true"
    >
      <el-form-item label="库位编码" prop="binCode">
        <el-input
          v-model="queryParams.binCode"
          placeholder="请输入库位编码"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="binStatus">
        <el-select
          v-model="queryParams.binStatus"
          placeholder="库位状态"
          clearable
          size="small"
          style="width: 240px"
        >
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="dateRange"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['wms:bin:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['wms:bin:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['wms:bin:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['wms:bin:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="binList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="库位序号" prop="id" width="120" />
      <el-table-column
        label="库位编码"
        prop="binCode"
        :show-overflow-tooltip="true"
        width="100"
      />
      <el-table-column
        label="库位条码"
        prop="binBarCode"
        :show-overflow-tooltip="true"
        width="100"
      />
      <el-table-column
        label="库位名称"
        prop="binName"
        :show-overflow-tooltip="true"
        width="100"
      />
      <el-table-column
        label="库位类型"
        prop="binType"
        :show-overflow-tooltip="true"
        width="100"
        :formatter="binTypeFormat"
      />
      <el-table-column
        label="库位温度"
        prop="binDeg"
        :show-overflow-tooltip="true"
        width="100"
        :formatter="binDegFormat"
      />
      <el-table-column
        label="所属仓库"
        prop="store.storeName"
        :show-overflow-tooltip="true"
        width="100"
      />
      <el-table-column
        label="所属客户"
        prop="customer.cusFullName"
        :show-overflow-tooltip="true"
        width="100"
      />
      <el-table-column label="状态" align="center" width="100">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.binStatus"
            active-value="0"
            inactive-value="1"
            @change="handleStatusChange(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['wms:bin:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['wms:bin:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改库位配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="库位编码" prop="binCode">
              <el-input v-model="form.binCode" placeholder="请输入库位编码" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="库位条码" prop="binBarCode">
              <el-input
                v-model="form.binBarCode"
                placeholder="请输入库位条码"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="库位名称" prop="binName">
              <el-input v-model="form.binName" placeholder="请输入库位名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="库位类型" prop="binType">
              <el-select v-model="form.binType" placeholder="请选择">
                <el-option
                  v-for="dict in binTypeOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="库位温度" prop="binDeg">
              <el-select v-model="form.binDeg" placeholder="请选择">
                <el-option
                  v-for="dict in binDegOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="所属仓库" prop="binStore">
              <el-select v-model="form.binStore" placeholder="请选择">
                <el-option
                  v-for="item in storeOptions"
                  :key="item.storeCode"
                  :label="item.storeName"
                  :value="item.storeCode"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="所属客户" prop="binCustomer">
              <el-select v-model="form.binCustomer" placeholder="请选择">
                <el-option
                  v-for="dict in customerOptions"
                  :key="dict.storeCode"
                  :label="dict.storeName"
                  :value="dict.storeCode"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="库位体积" prop="binVolume">
              <el-input v-model="form.binVolume" placeholder="请输入库位体积" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="库位面积" prop="binArea">
              <el-input v-model="form.binArea" placeholder="请输入库位面积" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="库位承重" prop="binWeight">
              <el-input v-model="form.binWeight" placeholder="请输入库位承重" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="库位长度" prop="binLength">
              <el-input v-model="form.binLength" placeholder="请输入库位长度" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="库位宽度" prop="binWidth">
              <el-input v-model="form.binWidth" placeholder="请输入库位宽度" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="库位高度" prop="binHeight">
              <el-input v-model="form.binHeight" placeholder="请输入库位高度" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="商品属性" prop="binGoodType">
              <el-input
                v-model="form.binGoodType"
                placeholder="请输入库位宽度"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="库位X坐标" prop="binXnode">
              <el-input v-model="form.binXnode" placeholder="请输入库位X坐标" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="库位Y坐标" prop="binYnode">
              <el-input v-model="form.binYnode" placeholder="请输入库位Y坐标" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="库位Z坐标" prop="binZnode">
              <el-input v-model="form.binZnode" placeholder="请输入库位Z坐标" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态">
              <el-radio-group v-model="form.binStatus">
                <el-radio
                  v-for="dict in statusOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue"
                  >{{ dict.dictLabel }}</el-radio
                >
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listBin,
  addBin,
  updateBin,
  delBin,
  exportBin,
  changeBinStatus,
} from "@/api/store/bin"
import {
  optionselect as getStoreOptionselect
} from "@/api/store/store";

export default {
  name: "Bin",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 角色表格数据
      binList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否显示弹出层（数据权限）
      openDataScope: false,
      menuExpand: false,
      menuNodeAll: false,
      deptExpand: true,
      deptNodeAll: false,
      // 日期范围
      dateRange: [],
      //库位类型数据字典
      binTypeOptions: [],
      //库位类型数据字典
      binDegOptions: [],
      //仓库选择框列表
      storeOptions: [],
      //客户选择框列表
      customerOptions: [],
      // 状态数据字典
      statusOptions: [],
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        storeCode: undefined,
        storeName: undefined,
        status: undefined,
      },
      // 表单参数
      form: {},
      defaultProps: {
        children: "children",
        label: "label",
      },
      // 表单校验
      rules: {
        binCode: [
          { required: true, message: "库位编码不能为空", trigger: "blur" },
        ],
        binBarCode: [
          { required: true, message: "库位条码不能为空", trigger: "blur" },
        ],
        binName: [
          { required: true, message: "库位名称不能为空", trigger: "blur" },
        ],
        binType: [
          { required: true, message: "库位类型不能为空", trigger: "blur" },
        ],
        binDeg: [
          { required: true, message: "库位温度不能为空", trigger: "blur" },
        ],
        binStore: [
          { required: true, message: "所属仓库不能为空", trigger: "blur" },
        ],
        binCustomer: [
          { required: true, message: "所属客户不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getList();
    this.getDicts("wms_bin_type").then((response) => {
      this.binTypeOptions = response.data;
    });
    this.getDicts("wms_bin_deg").then((response) => {
      this.binDegOptions = response.data;
    });
    this.getDicts("sys_normal_disable").then((response) => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    /** 查询库位列表 */
    getList() {
      this.loading = true;
      listBin(this.addDateRange(this.queryParams, this.dateRange)).then(
        (response) => {
          this.binList = response.rows;
          this.total = response.total;
          this.loading = false;
        }
      );
    },
    // 库位类型字典翻译
    binTypeFormat(row, column) {
      return this.selectDictLabel(this.binTypeOptions, row.binType);
    },
    // 库位温度字典翻译
    binDegFormat(row, column) {
      return this.selectDictLabel(this.binDegOptions, row.binDeg);
    },
    // 库位状态修改
    handleStatusChange(row) {
      let text = row.binStatus === 1 ? "停用" : "启用";
      this.$confirm("确认要" + text + '"' + row.binName + '"仓库吗?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return changeBinStatus(row.id, row.binStatus);
        })
        .then(() => {
          this.msgSuccess(text + "成功");
        })
        .catch(function () {
          row.binStatus = row.binStatus === 0 ? 1 : 0;
        });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      if (this.$refs.menu != undefined) {
        this.$refs.menu.setCheckedKeys([]);
      }
      (this.menuExpand = false),
        (this.menuNodeAll = false),
        (this.deptExpand = true),
        (this.deptNodeAll = false),
        (this.form = {
          id: undefined,
          storeCode: undefined,
          storeName: undefined,
          status: "0",
        });
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRange = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加库位";
      getStoreOptionselect().then(response => {
        this.storeOptions = response.data;
      })
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.form = row;
      this.open = true;
      this.title = "修改库位";
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != undefined) {
            updateBin(this.form).then((response) => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBin(this.form).then((response) => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除库位序号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return delBin(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有库位数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportBin(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        });
    },
  },
};
</script>